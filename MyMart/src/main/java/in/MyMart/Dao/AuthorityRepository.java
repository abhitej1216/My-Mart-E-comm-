package in.MyMart.Dao;

import in.MyMart.Model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

    public Authority findByRoleCode(String roleCode);
}
