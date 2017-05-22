package com.king.repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.king.core.Dependency;
import com.king.core.Parameter;
import com.king.core.ToolEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Tool entry repository.
 *
 * Created by king on 2017/5/9.
 */
@Repository
public class ToolEntryRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(ToolEntry entry) {
        entry.setCreateTime(new Date());
        int result = jdbcTemplate.update("insert into t_tool_entry(entry_name, dependency_json, parameter_json, code, belong_id, belong_name, is_public, create_time) values(?, ?, ?, ?, ?, ?, ?, ?)",
                entry.getEntryName(), JSON.toJSONString(entry.getDependencies()), JSON.toJSONString(entry.getParameters()), entry.getCode(), entry.getBelongId(), entry.getBelongName(), entry.isPublic(), entry.getCreateTime());
        if (result == 0)
            throw new RuntimeException("create tool entry error! entry = [" + entry + "].");
    }

    public void update(ToolEntry entry) {

    }

    @Transactional(readOnly = true)
    public ToolEntry getToolById(int toolId) {
        return jdbcTemplate.queryForObject("select * from t_tool_entry where entry_id = ?", new EntryRowMapper(), toolId);
    }

    @Transactional(readOnly = true)
    public List<ToolEntry> getAll() {
        return jdbcTemplate.query("select * from t_tool_entry", new EntryRowMapper());
    }

    public List<ToolEntry> getOpenTools() {
        return jdbcTemplate.query("select * from t_tool_entry where is_open = true", new EntryRowMapper());
    }

    private class EntryRowMapper implements RowMapper<ToolEntry> {

        @Override
        public ToolEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
            ToolEntry entry = new ToolEntry();
            entry.setEntryId(rs.getInt("entry_id"));
            entry.setEntryName(rs.getString("entry_name"));
            JSONArray depArr = JSON.parseArray(rs.getString("dependency_json"));
            entry.setDependencies(depArr.toJavaList(Dependency.class));

            JSONArray paraArr = JSON.parseArray(rs.getString("parameter_json"));
            entry.setParameters(paraArr.toJavaList(Parameter.class));

            entry.setCode(rs.getString("code"));
            entry.setBelongId(rs.getInt("belong_id"));
            entry.setBelongName(rs.getString("belong_name"));
            entry.setIsPublic(rs.getBoolean("is_public"));
            entry.setIsOpen(rs.getBoolean("is_open"));
            entry.setCreateTime(rs.getDate("create_time"));
            return entry;
        }
    }
}
