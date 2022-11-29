package com.boilerplate.modules.account.infra;

import com.boilerplate.modules.account.domain.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankingRepository extends JpaRepository<Ranking, Long> {

}
