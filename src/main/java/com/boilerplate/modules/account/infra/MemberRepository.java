package com.boilerplate.modules.account.infra;

import com.boilerplate.modules.account.domain.Member;
import com.boilerplate.modules.account.domain.RoleEnum;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {

  Optional<Member> findById(Long id);
  Optional<Member> findByNickname(String nickname);
  Optional<Member> findByEmail(String email);
  List<Member> findAllByRole(RoleEnum role);

  @Query(value = "SELECT * from(select id,dense_rank() over (order by point desc) "
      + "from bp.member) ranking where id = ?1", nativeQuery = true)
  Long findRankingById(Long id);
}

