package com.edutie.backend.domain.education.exercisetype.entities;

import com.edutie.backend.domain.common.paragraph.OrderedParagraph;
import com.edutie.backend.domain.education.exercisetype.identities.ReportTemplateParagraphId;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class ReportTemplateParagraph extends OrderedParagraph<ReportTemplateParagraphId> {
	public static ReportTemplateParagraph create(String title, String text) {
		ReportTemplateParagraph paragraph = new ReportTemplateParagraph();
		paragraph.setId(new ReportTemplateParagraphId());
		paragraph.setTitle(title);
		paragraph.setText(text);
		paragraph.setOrdinal(0);
		return paragraph;
	}
}
