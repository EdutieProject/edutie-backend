package com.edutie.backend.infrastructure.persistence.config.initialization;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.administration.administrator.persistence.AdministratorPersistence;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.persistence.LearningRequirementPersistence;
import com.edutie.backend.domain.personalization.learningresource.persistence.LearningResourcePersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
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
import com.edutie.backend.infrastructure.persistence.config.initialization.courses.SampleCourseSeeding;
import com.edutie.backend.infrastructure.persistence.config.initialization.courses.ThermodynamicsCourseSeeding;
import com.edutie.backend.infrastructure.persistence.config.initialization.samples.math.SampleModulusLearningRequirement;
import com.edutie.backend.infrastructure.persistence.config.initialization.samples.math.SampleQuadraticFunctionLearningRequirement;
import com.edutie.backend.infrastructure.persistence.config.initialization.samples.math.SampleSetsLearningRequirement;
import com.edutie.backend.infrastructure.persistence.config.initialization.samples.math.SampleTrigonometryLearningRequirement;
import com.edutie.backend.infrastructure.persistence.config.initialization.samples.physics.*;
import jakarta.annotation.PostConstruct;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.function.Consumer;

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
    final int MAX_SEEDED_COURSES = 1;
    final int MAX_SEEDED_SCIENCES = 1;
    private final SciencePersistence sciencePersistence;
    private final CoursePersistence coursePersistence;
    private final LessonPersistence lessonPersistence;
    private final SegmentPersistence segmentPersistence;
    private final AdministratorPersistence administratorPersistence;
    private final EducatorPersistence educatorPersistence;
    //    private final ExerciseTypePersistence exerciseTypePersistence;
    private final LearningRequirementPersistence learningRequirementPersistence;
    private final LearningResourceDefinitionPersistence learningResourceDefinitionPersistence;
    private final LearningResultPersistence learningResultPersistence;
    private final CourseTagPersistence courseTagPersistence;
    private final LearningResourcePersistence learningResourcePersistence;
    private final StudentPersistence studentPersistence;
    private final UserId uid = new UserId();
    private final Administrator administrator = Administrator.create(uid);
    private final Educator educator = Educator.create(uid, administrator);
    private final Student student = Student.create(new UserId());
    private CourseTag courseTag;
    private final SampleCourseSeeding sampleCourseSeeding;
    private final ThermodynamicsCourseSeeding thermodynamicsCourseSeeding;

    private void initializeProfiles() {
        log.info("Seeding profiles for user of id {}", uid);
        studentPersistence.save(student);
        administratorPersistence.save(administrator);
        educatorPersistence.save(educator);
    }

    private void initializeLearningRequirements() {
        log.info("Seeding learning requirements...");
        // ==== MATH - SAMPLE ====
        SampleModulusLearningRequirement.seedInDatabase(educator, learningRequirementPersistence);
        SampleQuadraticFunctionLearningRequirement.seedInDatabase(educator, learningRequirementPersistence);
        SampleTrigonometryLearningRequirement.seedInDatabase(educator, learningRequirementPersistence);
        SampleSetsLearningRequirement.seedInDatabase(educator, learningRequirementPersistence);
        // ==== PHYSICS ====
        SampleFirstLawThermodynamicsLearningRequirement.seedInDatabase(educator, learningRequirementPersistence);
        SampleHeatEngineLearningRequirement.seedInDatabase(educator, learningRequirementPersistence);
        SampleIdealGasTheoryLearningRequirement.seedInDatabase(educator, learningRequirementPersistence);
        SampleKineticMolecularTheoryLearningRequirement.seedInDatabase(educator, learningRequirementPersistence);
        SampleSecondLawThermodynamicsRequirement.seedInDatabase(educator, learningRequirementPersistence);
        SampleTemperatureAndHeatLearningRequirement.seedInDatabase(educator, learningRequirementPersistence);
        SampleThermalEngineEfficiencyRequirement.seedInDatabase(educator, learningRequirementPersistence);
    }

    public record SeededSegmentDetails(
            String segmentName,
            String segmentDescription,
            String learningResourceDefinitionTheoryOverview,
            String learningResourceDefinitionExerciseOverview,
            Set<LearningRequirement> learningRequirements
    ) {
    };

    /**
     * Seed database with sample study program
     * <p>This will create random number of sciences</p>
     *
     * @see #seedStudyProgram()
     * @since 0.5
     */
    @PostConstruct
    @Transactional
    public void seeding() {
        log.info("======================");
        log.info("  DB SEEDING - START  ");
        log.info("======================");
        initializeProfiles();
        initializeLearningRequirements();
        seedStudyProgram();
        log.info("=====================");
        log.info("  DB SEEDING - END   ");
        log.info("=====================");
    }

    /**
     * Seed random number of sciences
     *
     * @since 0.5
     */
    private void seedStudyProgram() {
        log.info("Seeding study program...");
        Science math = seedScience(
                "Matematyka", "Królowa nauk", "https://www.svgrepo.com/show/453302/mobius-strip.svg");
        Science physics = seedScience(
                "Fizyka", "Nauka badająca materię i jej zachowania", "https://www.svgrepo.com/show/452639/atom.svg");
        Science economy = seedScience(
                "Ekonomia", "Nauka nie tylko o pieniądzu", "https://www.svgrepo.com/show/452712/money-stack.svg");
        seedGivenCourse(math,
                "Przykładowy zestaw",
                "Ten zestaw to przykładowy zestaw z materiałami do nauki matematyki :) Jeśli chcesz poćwiczyć ogólne zagadnienia z różnych dziedzin, to może być dobry zestaw dla Ciebie",
                "https://www.svgrepo.com/show/452651/globe.svg",
                sampleCourseSeeding::sampleCourseLessonSeeding
        );
        seedGivenCourse(physics,
                "Termodynamika",
                "Zestaw zawierający zadania dzięki którym nauczysz się termodynamiki - dziedziny fizyki opisującej transfer energii pomiędzy ciałami.",
                "https://www.svgrepo.com/show/452675/bomb.svg",
                thermodynamicsCourseSeeding::thermodynamicsCourseSeeding
                );

    }

    private void seedGivenCourse(Science science, String name, String description, String imageSource, Consumer<Course> lessonSeedingFunction) {
        Course course = Course.create(educator, science);
        course.setName(name);
        course.setDescription(description);
        course.setImageSource(imageSource);
        coursePersistence.save(course);
        lessonSeedingFunction.accept(course);
    }

    /**
     * Seeds science
     *
     * @param name        science name
     * @param description science description
     * @return seeded science
     */
    private Science seedScience(String name, String description, String imageSource) {
        Science science = Science.create(educator).getValue();
        science.setName(name);
        science.setDescription(description);
        science.setImageSource(imageSource);
        sciencePersistence.save(science);
        return science;
    }


    // ==== RANDOM COURSE SEEDING BELOW ====

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
