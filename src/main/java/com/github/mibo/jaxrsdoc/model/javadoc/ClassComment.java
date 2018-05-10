package com.github.mibo.jaxrsdoc.model.javadoc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClassComment extends MemberComment {

    private List<MemberParameterTag> fieldComments = new ArrayList<>();

    public ClassComment(String comment, Map<Integer, String> responseComments, boolean deprecated) {
        super(comment, responseComments, deprecated);
    }

    public List<MemberParameterTag> getFieldComments() {
        return fieldComments;
    }

}
