package com.lingfenglong.rpc.entity;

import java.io.Serializable;

public record User(Integer id, String name, Integer age) implements Serializable {
}
