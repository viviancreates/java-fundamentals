package org.example.model;


//why using a record?
//bc it is immutable, we are going to create a party and will not edit it once it is created
//no need to create constructor or setters/getters
public record Party(String name, int size) {

}
