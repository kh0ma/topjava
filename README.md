[![Codacy Badge](https://api.codacy.com/project/badge/Grade/da8fb8eab521431da20007395881cb4f)](https://www.codacy.com/app/khomenko-dp/topjava?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=kh0ma/topjava&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/kh0ma/topjava.svg?branch=master)](https://travis-ci.org/kh0ma/topjava)
[![Dependency Status](https://dependencyci.com/github/kh0ma/topjava/badge)](https://dependencyci.com/github/kh0ma/topjava)

Java Enterprise Online Project 
===============================

Java Enterprise project with registration/authorization and role-based interface (USER, ADMIN). The administrator can create/edit/delete/users and the user can manage his profile and data (day, food, calories) through the UI (via AJAX) and REST interface with basic authentication. Data can be filtered by date and time and the color of entry in table depends on whether the amount of calories per day more than norm (it's editable parameter in profile). All REST interface covered with JUnit tests using Spring MVC Test and Spring Security Test.

DEMO: http://topjava.herokuapp.com/

The project is made to gain expirience so some of this functionality is duplicated just to use a variety of techniques for practice (e.g. access to the database is made via JDBC, JPA and Spring DataJpa).
