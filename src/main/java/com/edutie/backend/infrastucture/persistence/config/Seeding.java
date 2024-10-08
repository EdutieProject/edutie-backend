package com.edutie.backend.infrastucture.persistence.config;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.administration.administrator.persistence.AdministratorPersistence;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.entities.Activity;
import com.edutie.backend.domain.personalization.learningresource.entities.Theory;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.LearningResourceGenerationSchema;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import com.edutie.backend.domain.studyprogram.course.tag.CourseTag;
import com.edutie.backend.domain.studyprogram.course.tag.persistence.CourseTagPersistence;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.persistence.SciencePersistence;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.domain.studyprogram.segment.persistence.SegmentPersistence;
import jakarta.annotation.PostConstruct;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

/**
 * Seeding class for seeding database with sample data
 *
 * @author Kjur0
 * @version 0.6
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class Seeding {
    private static LearningResourceDefinitionId learningResourceDefinitionId;
    final int MAX_SEEDED_COURSES = 8;
    final int MAX_SEEDED_SCIENCES = 4;
    private final SciencePersistence sciencePersistence;
    private final CoursePersistence coursePersistence;
    private final LessonPersistence lessonPersistence;
    private final SegmentPersistence segmentPersistence;
    private final AdministratorPersistence administratorPersistence;
    private final EducatorPersistence educatorPersistence;
    //    private final ExerciseTypePersistence exerciseTypePersistence;
    private final LearningRequirementPersistence learningRequirementPersistence;
    private final LearningResourceDefinitionPersistence learningResourceDefinitionPersistence;
    private final CourseTagPersistence courseTagPersistence;
    private final LearningResourcePersistence learningResourcePersistence;
    private final UserId uid = new UserId();
    private final Administrator administrator = Administrator.create(uid);
    private final Educator educator = Educator.create(uid, administrator);
    private CourseTag courseTag;

    /**
     * Seed database with sample study program
     * <p>This will create random number of sciences</p>
     *
     * @see #seedSciences()
     * @since 0.5
     */
    @PostConstruct
    @Transactional
    public void seedStudyProgram() {
        log.info("======================");
        log.info("  DB SEEDING - START  ");
        log.info("======================");
        administratorPersistence.save(administrator);
        educatorPersistence.save(educator);
        seedLearningResourceDefinition();
        seedSciences();
        injectLearningResourceDefinition();
        log.info("=====================");
        log.info("  DB SEEDING - END   ");
        log.info("=====================");
    }

    private void injectLearningResourceDefinition() {
        log.info("Injecting LRD into one of the segments...");
        Segment segment = segmentPersistence.getRepository().findAll(Sort.by("createdOn")).get(2);
        segment.setName("Podróż oceaniczna z wartością bezwzględną");
        segment.setSnippetDescription("W tym segmencie poznasz wartość bezwzględną wykonując zadania opisujące morską podróż statkiem! Naszykuj się na morską przygodę z zeszytem");
        segment.setLearningResourceDefinitionId(learningResourceDefinitionId);
        segmentPersistence.save(segment);
    }

    /**
     * Seed random number of sciences
     *
     * @see #seedSciences(int)
     * @since 0.5
     */
    private void seedSciences() {
        log.info("Seeding study program...");
        int sciencesCount = (int) Math.ceil(Math.random() * MAX_SEEDED_SCIENCES);
        seedSciences(sciencesCount);
    }

    /**
     * Seed number of sciences
     *
     * @param sciences number of sciences to seed
     * @see #seedScience(int)
     * @since 0.5
     */
    private void seedSciences(int sciences) {
        for (int i = 0; i < sciences; i++)
            seedScience(i);
    }

    /**
     * Seed science
     *
     * @param i science number
     * @see #seedCourses(Science)
     * @since 0.5
     */
    private void seedScience(int i) {
        Science science = Science.create(educator).getValue();
        science.setName("Science" + i);
        science.setDescription("Description of Science" + i);
        science.update(uid);
        sciencePersistence.save(science);
        seedCourses(science);
    }

    /**
     * Seed random number of courses in science
     *
     * @param science science in which the courses will be created
     * @see #seedCourse(int, Science)
     * @since 0.5
     */
    private void seedCourses(Science science) {
        int courses = (int) Math.ceil(Math.random() * MAX_SEEDED_COURSES);
        for (int i = 0; i < courses; i++)
            seedCourse(i, science);
    }

    /**
     * Seed course
     *
     * @param i       courser number
     * @param science science in which the course will be created
     * @see #seedLessons(Course)
     * @since 0.5
     */
    private void seedCourse(int i, Science science) {
        Course course = Course.create(educator, science);
        course.setName("Course" + i);
        course.setDescription("Description of Course" + i);
        course.setAccessible(true);

        if (courseTag != null)
            course.addTag(courseTag);
        courseTag = CourseTag.create("Course Tag " + i);
        courseTagPersistence.save(courseTag);
        course.addTag(courseTag);

        course.update(uid);
        coursePersistence.save(course);
        seedLessons(course);
    }

    /**
     * Seed lessons in course
     *
     * @param course course to seed
     * @see LessonSeeder#seedLessons()
     * @since 0.5
     */
    private void seedLessons(Course course) {
        LessonSeeder ls = new LessonSeeder(course);
        ls.seedLessons();
    }

    private void seedLearningResourceDefinition() {
        LearningRequirement learningRequirement1 = LearningRequirement.create(educator);
        learningRequirement1.setKnowledgeSubjectId(new KnowledgeSubjectId(UUID.fromString("3dcf1a7d-d9ea-4e9b-becb-af730841056f")));
        learningRequirement1.setName(SampleModulusLearningRequirementData.LEARNING_REQUIREMENT_NAME);
        learningRequirement1.appendSubRequirement(
                "Uczeń zna definicję wartości bezwzględnej liczby rzeczywistej i jej interpretację geometryczną",
                PromptFragment.of(SampleModulusLearningRequirementData.SUB_REQUIREMENT_1)
        );
        learningRequirement1.appendSubRequirement(
                "Uczeń potrafi obliczyć wartość bezwzględną liczby",
                PromptFragment.of(SampleModulusLearningRequirementData.SUB_REQUIREMENT_2)
        );
        learningRequirement1.appendSubRequirement(
                "Uczeń umie zapisać i obliczyć odległość na osi liczbowej między dwoma dowolnymi punktami",
                PromptFragment.of(SampleModulusLearningRequirementData.SUB_REQUIREMENT_3)
        );
        learningRequirement1.appendSubRequirement(
                "Uczeń zaznacza na osi liczbowej liczby o danej wartości bezwzględnej",
                PromptFragment.of(SampleModulusLearningRequirementData.SUB_REQUIREMENT_4)
        );
        learningRequirement1.appendSubRequirement(
                "Uczeń rozwiązuje proste równania z wartością bezwzględną typu |x-a| = b",
                PromptFragment.of(SampleModulusLearningRequirementData.SUB_REQUIREMENT_5)
        );
        learningRequirement1.appendSubRequirement(
                "Uczeń potrafi zaznaczyć na osi liczbowej zbiory opisane za pomocą równań i nierówności z wartością bezwzględną typu: | x - a | = b, | x - a | < b, | x - a | > b",
                PromptFragment.of(SampleModulusLearningRequirementData.SUB_REQUIREMENT_6)
        );
        learningRequirement1.appendSubRequirement(
                "Uczeń potrafi uprościć wyrażenie z wartością bezwzględną dla zmiennej z danego przedziału",
                PromptFragment.of(SampleModulusLearningRequirementData.SUB_REQUIREMENT_7)
        );
        learningRequirement1.appendSubRequirement(
                "Uczeń potrafi na podstawie zbioru rozwiązań nierówności z wartością bezwzględną zapisać tę nierówność",
                PromptFragment.of(SampleModulusLearningRequirementData.SUB_REQUIREMENT_8)
        );

        learningRequirementPersistence.save(learningRequirement1).throwIfFailure();

        LearningRequirement learningRequirement2 = LearningRequirement.create(educator);
        learningRequirement2.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningRequirement2.setName("Funkcja kwadratowa");
        learningRequirement2.appendSubRequirement(
                "Związek między wzorem funkcji kwadratowej w postaci ogólnej, a wzorem funkcji kwadratowej w postaci kanonicznej",
                PromptFragment.of(SampleQuadraticFunctionLearningRequirementData.SUB_REQUIREMENT_1)
        );
        learningRequirement2.appendSubRequirement(
                "Miejsce zerowe funkcji kwadratowej. Wzór funkcji kwadratowej w postaci iloczynowej",
                PromptFragment.of(SampleQuadraticFunctionLearningRequirementData.SUB_REQUIREMENT_2)
        );
        learningRequirement2.appendSubRequirement(
                "Szkicowanie wykresów funkcji kwadratowych. Odczytywanie własności funkcji kwadratowej na podstawie wykresu",
                PromptFragment.of(SampleQuadraticFunctionLearningRequirementData.SUB_REQUIREMENT_3)
        );
        learningRequirement2.appendSubRequirement(
                "Wyznaczanie wzoru funkcji kwadratowej na podstawie jej własności.",
                PromptFragment.of(SampleQuadraticFunctionLearningRequirementData.SUB_REQUIREMENT_4)
        );
        learningRequirement2.appendSubRequirement(
                "Najmniejsza oraz największa wartość funkcji kwadratowej w przedziale domkniętym",
                PromptFragment.of(SampleQuadraticFunctionLearningRequirementData.SUB_REQUIREMENT_5)
        );
        learningRequirement2.appendSubRequirement(
                "Uczeń potrafi algebraicznie rozwiązywać równania kwadratowe z jedną niewiadomą; ",
                PromptFragment.of(SampleQuadraticFunctionLearningRequirementData.SUB_REQUIREMENT_6)
        );
        learningRequirementPersistence.save(learningRequirement2).throwIfFailure();

        LearningResourceDefinition learningResourceDefinition = LearningResourceDefinition.create(
                educator,
                PromptFragment.of("Opisz również rozwiązywanie równań kwadratowych z wartością bezwzględną. Dopasuj trudność do trudności podanych wymagań"),
                PromptFragment.of("""
                Zadanie powinno zawierać fabułę dotyczącą projektowania miasta. 
                Niech problemy dotyczą wytyczenia miejsc ścieżek, budynków, tras autobusowych lub ulic. 
                Niech zadanie uwzględnia chociaż jeden przykład równania kwadratowego z wartością bezwzględną.
                Przykład ten powinien być dopasowany trudnością do trudności podanych wcześniej wymagań
                """),
                PromptFragment.of("Niech cała twoja odpowiedź składa się z diagramu mermaid opisującego proces rozwiązywania przykładowego wymagania nauczania"),
                PromptFragment.of("Niech conajmniej jedna z podpowiedzi nakreśla uczniowi jak rozwiązywać równania kwadratowe z wartością bezwzględną."),
                Set.of(learningRequirement1, learningRequirement2)
        );
        learningResourceDefinitionPersistence.save(learningResourceDefinition);
        log.info("Seeded LRD with id: {}", learningResourceDefinition.getId().identifierValue().toString());
        learningResourceDefinitionId = learningResourceDefinition.getId();

        LearningResourceGenerationSchema learningResourceGenerationSchema = LearningResourceGenerationSchema.create(learningResourceDefinition, Student.create(new UserId()));
        LearningResource learningResource = LearningResource.create(learningResourceGenerationSchema, Activity.create("Please calculate the hypotenuse in the right triangle of sides lengths: 6 and 8", Set.of()), Theory.create("Pythagoras theorem is...", "Pythagoras helps to calculate hypotenuses"), Set.of());
        learningResourcePersistence.save(learningResource).throwIfFailure();
        log.info("Seeded Learning Resource with id: {}", learningResource.getId().identifierValue().toString());
    }

    /**
     * Helper class for seeding lessons in courses using predefined variants
     *
     * @author Kjur0
     * @version 0.5
     * @since 0.5
     */
    private class LessonSeeder {
        private final Course course;

        /**
         * Constructor of lesson seeder
         *
         * @param course course in which lessons will be seeded
         */
        public LessonSeeder(Course course) {
            this.course = course;
        }

        /**
         * Seed lessons in course using random variant
         *
         * @see LessonSeeder#variant1()
         * @see LessonSeeder#variant2()
         * @since 0.5
         */
        @Transactional
        public void seedLessons() {
            switch ((int) Math.ceil(Math.random() * 2)) {
                case 1 -> variant1();
                case 2 -> variant2();
            }
        }

        /**
         * Seed first lesson in course
         *
         * @return first lesson
         * @since 0.5
         */
        private @NonNull Lesson seedFirstLesson() {
            Lesson lesson = Lesson.create(educator, course);
            lesson.setName("Lesson 1");
            lesson.setDescription("Description of Lesson 1");
            lesson.update(uid);
            lessonPersistence.save(lesson);
            seedSegments(lesson);
            return lesson;
        }

        /**
         * Variant 1
         * <details><pre class="mermaid">
         * graph
         *  l1 --> l2 --> l3
         *  l2 --> l4 --> l5 --> l6 --> l7
         *  l5 --> l8 --> l9
         * </pre></details>
         *
         * @see #seedFirstLesson()
         * @see #seedLesson(int, Lesson)
         * @since 0.5
         **/
        public void variant1() {
            Lesson l1 = seedFirstLesson();

            Lesson l2 = seedLesson(2, l1);
            seedLesson(3, l2);

            l1 = seedLesson(5, seedLesson(4, l2));
            seedLesson(7, seedLesson(6, l1));

            seedLesson(9, seedLesson(8, l1));
        }

        /**
         * Variant 2
         * <details><pre class="mermaid">
         * graph
         *  l1 --> l2 --> l3 & l4
         *  l1 --> l5 & l6
         *  l3 --> l7 --> l8 --> l9 & l10
         *  l7 --> l11
         *  l4 --> l12 & l13
         *  l13 --> l14 --> l15 & l16
         *  l16 --> l17
         *  l5 --> l18 --> l19
         *  l6 --> l20 --> l21 --> l22 & l23 & l24
         *  l23 --> l25
         *  l24 --> l26 --> l27
         * </pre></details>
         *
         * @see #seedFirstLesson()
         * @see #seedLesson(int, Lesson)
         * @since 0.5
         */
        public void variant2() {
            Lesson l1 = seedFirstLesson();

            Lesson l2 = seedLesson(2, l1);

            Lesson l3 = seedLesson(7, seedLesson(3, l2));
            seedLesson(11, l2);
            l3 = seedLesson(8, l3);
            seedLesson(9, l3);
            seedLesson(10, l3);

            l3 = seedLesson(4, l2);
            seedLesson(12, l3);
            l3 = seedLesson(14, seedLesson(13, l3));
            seedLesson(15, l3);
            seedLesson(17, seedLesson(16, l3));

            seedLesson(19, seedLesson(18, seedLesson(5, l1)));

            l2 = seedLesson(21, seedLesson(20, seedLesson(6, l1)));
            seedLesson(22, l2);
            seedLesson(25, seedLesson(23, l2));
            seedLesson(27, seedLesson(26, seedLesson(24, l2)));
        }

        /**
         * Seed lesson in course
         *
         * @param i    lesson number
         * @param prev previous lesson
         * @return seeded lesson
         * @see #seedSegments(Lesson)
         * @since 0.5
         */
        private @NonNull Lesson seedLesson(int i, Lesson prev) {
            Lesson lesson = Lesson.create(educator, course);
            lesson.setName("Lesson" + i);
            lesson.setDescription("Description of Lesson" + i);
            lesson.setPreviousElement(prev);
            lesson.update(uid);
            lessonPersistence.save(lesson);
            seedSegments(lesson);
            return lesson;
        }

        /**
         * Seed segments in lesson
         *
         * @param lesson lesson to seed
         * @see SegmentSeeder#seedSegments()
         * @since 0.5
         */
        private void seedSegments(Lesson lesson) {
            SegmentSeeder ss = new SegmentSeeder(lesson);
            ss.seedSegments();
        }

        /**
         * Helper class for seeding segments in lessons using predefined variants
         *
         * @author Kjur0
         * @version 0.5
         * @since 0.5
         */
        private class SegmentSeeder {
            //			private final ExerciseType et;
            private final Lesson lesson;

            /**
             * Constructor
             *
             * @param lesson lesson to seed the segments into
             */
            public SegmentSeeder(Lesson lesson) {
                this.lesson = lesson;

//				et = ExerciseType.create(educator).getValue();
//				et.setName("Exercise Type 1");
//				et.setDescription(PromptFragment.of("Description of Exercise Type 1"));

//				exerciseTypePersistence.save(et);
            }

            /**
             * Seed segments in lesson using random variant
             *
             * @see SegmentSeeder#variant1()
             * @see SegmentSeeder#variant2()
             * @see SegmentSeeder#variant3()
             * @since 0.5
             */
            @Transactional
            public void seedSegments() {
                switch ((int) Math.ceil(Math.random() * 3)) {
                    case 1 -> variant1();
                    case 2 -> variant2();
                    case 3 -> variant3();
                }
            }

            /**
             * Seed first segment in lesson
             *
             * @return first segment
             * @since 0.6
             */
            private @NonNull Segment seedFirstSegment() {
//				Science science = lesson.getCourse().getScience();
//				LearningRequirement lr1 = LearningRequirement.create(educator, science).getValue();
//				lr1.setName("Learning Requirement 1");
//				lr1.setDescription(PromptFragment.of("Description of Learning Requirement 1"));
//				learningRequirementPersistence.save(lr1);

                Segment s1 = Segment.create(educator, lesson);
                s1.setName("Segment 1");
                s1.setSnippetDescription("Snippet description of Segment 1");
                s1.update(uid);
                segmentPersistence.save(s1);

                return s1;
            }

            /**
             * Variant 1
             * <details><pre class="mermaid">
             * graph
             *  s1 --> s2 --> s3
             *  s2 --> s4 --> s5 --> s6 --> s7
             *  s5 --> s8 --> s9
             * </pre></details>
             *
             * @see #seedFirstSegment()
             * @see #seedSegment(int, Segment)
             * @since 0.5
             **/
            public void variant1() {
                Segment s1 = seedFirstSegment();

                Segment s2 = seedSegment(2, s1);
                seedSegment(3, s2);

                s1 = seedSegment(5, seedSegment(4, s2));
                seedSegment(7, seedSegment(6, s1));

                seedSegment(9, seedSegment(8, s1));
            }

            /**
             * Variant 2
             * <details><pre class="mermaid">
             * graph
             *  l1 --> l2 --> l3 & l4
             *  l1 --> l5 & l6
             *  l3 --> l7 --> l8 --> l9 & l10
             *  l7 --> l11
             *  l4 --> l12 & l13
             *  l13 --> l14 --> l15 & l16
             *  l16 --> l17
             *  l5 --> l18 --> l19
             *  l6 --> l20 --> l21 --> l22 & l23 & l24
             *  l23 --> l25
             *  l24 --> l26 --> l27
             * </pre></details>
             *
             * @see #seedFirstSegment()
             * @see #seedSegment(int, Segment)
             * @since 0.5
             */
            public void variant2() {
                Segment s1 = seedFirstSegment();

                Segment s2 = seedSegment(2, s1);

                Segment s3 = seedSegment(7, seedSegment(3, s2));
                seedSegment(11, s3);
                s3 = seedSegment(8, s3);
                seedSegment(9, s3);
                seedSegment(10, s3);

                s3 = seedSegment(4, s2);
                seedSegment(12, s3);
                s3 = seedSegment(14, seedSegment(13, s3));
                seedSegment(15, s3);
                seedSegment(17, seedSegment(16, s3));

                seedSegment(19, seedSegment(18, seedSegment(5, s1)));

                s2 = seedSegment(21, seedSegment(20, seedSegment(6, s1)));
                seedSegment(22, s2);
                seedSegment(25, seedSegment(23, s2));
                seedSegment(27, seedSegment(26, seedSegment(24, s2)));
            }

            /**
             * Variant 3
             * <details><pre class="mermaid">
             * graph
             *  s1 --> s2 & s3 & s4
             *  s2 --> s5 --> s6 --> s7 & s8
             *  s5 --> s9
             *  s3 --> s10 & s11
             *  s11 --> s12 --> s13 & s14
             *  s14 --> s15
             *  s4 --> s16 --> s17
             *  s16 --> s18 --> s19
             * </pre></details>
             *
             * @see #seedFirstSegment()
             * @see #seedSegment(int, Segment)
             * @since 0.5
             */
            public void variant3() {
                Segment s1 = seedFirstSegment();

                Segment s2 = seedSegment(5, seedSegment(2, s1));
                seedSegment(9, s2);
                Segment s3 = seedSegment(6, s2);
                seedSegment(7, s3);
                seedSegment(8, s3);

                s2 = seedSegment(3, s1);
                seedSegment(10, s2);
                s3 = seedSegment(12, seedSegment(11, s2));
                seedSegment(13, s3);
                seedSegment(15, seedSegment(14, s3));

                s2 = seedSegment(16, seedSegment(4, s1));
                seedSegment(17, s2);
                seedSegment(19, seedSegment(18, s2));
            }

            /**
             * Seed segment in lesson
             *
             * @param i    lesson number
             * @param prev previous lesson
             * @return seeded lesson
             * @since 0.6
             */
            private @NonNull Segment seedSegment(int i, Segment prev) {
                Segment segment = Segment.create(educator, lesson);
                segment.setName("Segment" + i);
                segment.setSnippetDescription("Snippet description of Segment" + i);
                segment.setPreviousElement(prev);
                segment.update(uid);
                segmentPersistence.save(segment);
                return segment;
            }
        }
    }

}
