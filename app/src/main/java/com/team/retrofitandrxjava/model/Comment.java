package com.team.retrofitandrxjava.model;

import java.io.Serializable;

/**
 * Created by JongHunLee on 2015-07-09.
 */
public class Comment implements Serializable {

    public int postId;
    public int id;
    public String name;
    public String email;
    public String body;
}
