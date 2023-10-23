package kr.co.sboard.repository;

import kr.co.sboard.entity.ArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity,Integer> {

    /*
    @Query("SELECT a FROM Article a WHERE a.parent = 0")
    List<ArticleDTO> findArticlesWithParentZero();
    */

    public Page<ArticleEntity> findByParent(int parent, Pageable pageable);

//   원래라면 다음처럼 findby안에 엔티티명이 들어가야 한다. public void findByArticleEntityParent
}
