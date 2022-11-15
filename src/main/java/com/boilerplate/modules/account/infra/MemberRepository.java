package com.boilerplate.modules.account.infra;

import com.boilerplate.modules.account.application.response.RankingResponseDto;
import com.boilerplate.modules.account.domain.Member;
import com.boilerplate.modules.account.domain.RoleEnum;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Optional<Member> findById(Long id);

	Optional<Member> findByNickname(String nickname);

	Optional<Member> findByEmail(String email);

	List<Member> findAllByRole(RoleEnum role);

	@Modifying
	@Transactional
	@Query(value = "with\n"
		+ "    newRank as (\n"
		+ "        select id, dense_rank() over (\n"
		+ "            ORDER BY point desc, id\n"
		+ "            ) ranking\n"
		+ "        from member\n"
		+ "    )\n"
		+ "update member m, newRank r\n"
		+ "set m.ranking = r.ranking\n"
		+ "where m.id = r.id;"
		, nativeQuery = true)
	void saveAllRanking();



}

//50만명 가입 / 랭ㅋ팅 원투중