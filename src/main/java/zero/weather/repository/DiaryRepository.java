package zero.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zero.weather.domain.Diary;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Integer> {

}
