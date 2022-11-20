package org.vasshaug.demicontt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/*
Contains the results element from randomuser
{
  "results": [
    {
        <User class>
    }
    ],
  "info": {
    "seed": "eb7bffcf8d513e9a",
    "results": 10,
    "page": 1,
    "version": "1.4"
  }
}
"info" element is ignored
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Result {
    private static final Logger logger = LoggerFactory.getLogger(Result.class);
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    @OneToMany(mappedBy = "result", cascade = CascadeType.ALL)
    private List<User> results;

    public Result() {
    }

    public List<User> getResults() {
        return results;
    }

    public void setResults(List<User> results) {
        this.results = results;
        logger.info("Results.size " + results.size());
//        results.forEach(  (user) -> user.setResult(this));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Result)) return false;
        Result that = (Result) o;
        return Objects.equals(id, that.id) && Objects.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, results);
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", results=" + results +
                '}';
    }
}