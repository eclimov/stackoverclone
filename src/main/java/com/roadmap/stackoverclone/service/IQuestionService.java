package com.roadmap.stackoverclone.service;


import com.roadmap.stackoverclone.constant.EntityConstants;
import com.roadmap.stackoverclone.constraint.VerifyRightsParameters;
import com.roadmap.stackoverclone.model.data.TextData;

import java.util.List;

public interface IQuestionService {
    List<TextData> get();

    TextData create(TextData source);

    TextData findById(Long id);

    @VerifyRightsParameters(objectType = EntityConstants.TYPE_QUESTIONS)
    TextData update(Long id, TextData source);

    @VerifyRightsParameters(objectType = EntityConstants.TYPE_QUESTIONS)
    void delete(Long id);

    void voteUp(Long id);

    void voteDown(Long id);
}
