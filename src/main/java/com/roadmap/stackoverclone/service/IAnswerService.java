package com.roadmap.stackoverclone.service;

import com.roadmap.stackoverclone.constant.EntityConstants;
import com.roadmap.stackoverclone.constraint.VerifyRightsParameters;
import com.roadmap.stackoverclone.model.data.TextData;

import java.util.List;

public interface IAnswerService {
    List<TextData> findByQuestionId(Long questionId);

    TextData findById(Long id);

    TextData create(TextData source, Long questionId);

    @VerifyRightsParameters(objectType = EntityConstants.TYPE_ANSWERS)
    TextData update(Long id, TextData source);

    @VerifyRightsParameters(objectType = EntityConstants.TYPE_ANSWERS)
    void delete(Long id);

    void voteUp(Long id);

    void voteDown(Long id);
}
