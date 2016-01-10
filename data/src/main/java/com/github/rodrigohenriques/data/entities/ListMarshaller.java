package com.github.rodrigohenriques.data.entities;

import java.util.ArrayList;
import java.util.List;

public class ListMarshaller<Input, Output> implements Marshaller<List<Input>, List<Output>> {

    Marshaller<Input, Output> marshaller;

    public ListMarshaller(Marshaller<Input, Output> marshaller) {
        this.marshaller = marshaller;
    }

    @Override
    public List<Output> marshal(List<Input> inputs) {
        List<Output> resultList = new ArrayList<>(inputs.size());

        for (Input input: inputs) {
            Output output = marshaller.marshal(input);
            resultList.add(output);
        }

        return resultList;
    }
}
