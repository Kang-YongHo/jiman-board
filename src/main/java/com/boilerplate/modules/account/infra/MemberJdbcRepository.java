package com.boilerplate.modules.account.infra;

import com.boilerplate.modules.account.domain.Member;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public void saveAll(List<Member> list){
        jdbcTemplate.batchUpdate("insert into member(nickname, email, password, point, ranking, role) "
            + "values (?,?,?,?,?,?)", new BatchPreparedStatementSetter(){

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,list.get(i).getNickname());
                ps.setString(2,list.get(i).getEmail());
                ps.setString(3,list.get(i).getPassword());
                ps.setLong(4,list.get(i).getPoint());
                ps.setLong(5,0L);
                ps.setString(6, String.valueOf(list.get(i).getRole()));
            }

            @Override
            public int getBatchSize() {
                return list.size();
            }
        });
    }
}
