/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics;

import statistics.matcher.*;

/**
 *
 * @author toniramo
 */
public class QueryBuilder {

    private Matcher matcher;

    public QueryBuilder() {
        this.initialize();
    }

    public Matcher matcher() {
        return matcher;
    }

    public QueryBuilder hasAtLeast(int value, String category) {
        this.matcher = new And(matcher, new HasAtLeast(value, category));
        return this;
    }

    public QueryBuilder hasFewerThan(int value, String category) {
        this.matcher = new And(matcher, new HasFewerThan(value, category));
        return this;
    }

    public QueryBuilder playsIn(String team) {
        this.matcher = new And(matcher, new PlaysIn(team));
        return this;
    }

    public QueryBuilder oneOf(Matcher m1, Matcher m2) {
        this.matcher = new Or(m1, m2);
        return this;
    }

    public Matcher build() {
        Matcher m = this.matcher;
        this.initialize();
        return m;
    }

    private void initialize() {
        this.matcher = new All();
    }
}
