package com.edy.first_restapi.Mapper;

public interface Mapper<A, B> {

    B mapto(A a);
    
    A mapfrom(B b);

}
