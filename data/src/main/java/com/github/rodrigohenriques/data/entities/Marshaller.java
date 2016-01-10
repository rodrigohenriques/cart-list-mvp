package com.github.rodrigohenriques.data.entities;

public interface Marshaller<Input, Output> {
    Output marshal(Input input);
}
