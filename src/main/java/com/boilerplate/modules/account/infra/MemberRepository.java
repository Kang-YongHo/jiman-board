package com.boilerplate.modules.account.infra;

import com.boilerplate.modules.account.application.response.RankingResponseDto;
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

	@Query(value = "select * "
		+ "from "
		+ "(select id, dense_rank()over(order by point desc) "
		+ "as ranking "
		+ "from bp.member) ranking "
		+ "where id = ?1"
		, nativeQuery = true)
	RankingInterface findRankingById(Long id);

	@Query(value = "select * from (select id, dense_rank()over(order by point desc) as ranking from bp.member) ranking;"
		, nativeQuery = true)
	List<RankingInterface> findAllRanking();



}

//50만명 가입 / 랭ㅋ팅 원투중