package com.example.rampagegg.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.rampagegg.model.Hero;
import com.example.rampagegg.model.Item;
import com.example.rampagegg.model.heroesstats.HeroesStats;
import com.example.rampagegg.model.matches.Matches;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface DotaDao {

    @Insert(onConflict = REPLACE)
    void insertAllHeroes(List<Hero> heroList);

    @Insert(onConflict = REPLACE)
    void insertAllItems(List<Item> itemList);

    @Insert(onConflict = REPLACE)
    void insertAllMatches(List<Matches> matchesList);

    @Insert(onConflict = REPLACE)
    void insertAllHeroesStats(List<HeroesStats> heroesStatsList);

    @Query("DELETE FROM table_matches")
    void deleteAllMatches();

    @Query("DELETE FROM table_heroes_stats")
    void deleteAllMHeroesStats();

    @Query("SELECT * FROM table_matches ORDER BY start_time DESC")
    LiveData<List<Matches>> getAllMatches();

    @Query("SELECT SUM(games) FROM table_heroes_stats WHERE games != 0 ORDER BY games DESC")
    float getTotalGames();

    @Query("SELECT * FROM table_heroes_stats WHERE games != 0 ORDER BY games DESC")
    LiveData<List<HeroesStats>> getAllHeroesStats();

    @Query("SELECT name FROM table_heroes WHERE id = :heroId")
    String findHeroName(Long heroId);

    @Query("SELECT * FROM table_items WHERE id = :itemId")
    Item findItemName(Long itemId);

    @Transaction
    public default void updateMatches(List<Matches> matchesList){
        deleteAllMatches();
        insertAllMatches(matchesList);
    }
}
