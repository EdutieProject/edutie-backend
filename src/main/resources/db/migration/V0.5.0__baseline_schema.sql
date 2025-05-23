--
-- PostgreSQL database dump
--

-- Dumped from database version 16.4 (Debian 16.4-1.pgdg120+1)
-- Dumped by pg_dump version 16.4 (Debian 16.4-1.pgdg120+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: activity; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.activity (
    id uuid NOT NULL,
    activity_text text
);


ALTER TABLE public.activity OWNER TO postgres;

--
-- Name: activity_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.activity_details (
    id uuid NOT NULL,
    exercise_description text,
    hints_description text
);


ALTER TABLE public.activity_details OWNER TO postgres;

--
-- Name: activity_hints; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.activity_hints (
    activity_id uuid NOT NULL,
    hints_id uuid NOT NULL
);


ALTER TABLE public.activity_hints OWNER TO postgres;

--
-- Name: administrator; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.administrator (
    assigned_on timestamp(6) without time zone,
    id uuid NOT NULL,
    user_id uuid
);


ALTER TABLE public.administrator OWNER TO postgres;

--
-- Name: assessment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.assessment (
    grade_number integer,
    id uuid NOT NULL,
    learning_requirement_id uuid,
    feedback_text text
);


ALTER TABLE public.assessment OWNER TO postgres;

--
-- Name: assessment_qualified_elemental_requirements; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.assessment_qualified_elemental_requirements (
    assessment_id uuid NOT NULL,
    qualified_elemental_requirements_id uuid NOT NULL
);


ALTER TABLE public.assessment_qualified_elemental_requirements OWNER TO postgres;

--
-- Name: course; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.course (
    accessible boolean NOT NULL,
    created_on timestamp without time zone NOT NULL,
    updated_on timestamp(6) without time zone,
    author_educator_id uuid,
    create_user_id uuid,
    id uuid NOT NULL,
    science_id uuid,
    update_user_id uuid,
    description character varying(255),
    image_source character varying(255),
    name character varying(255)
);


ALTER TABLE public.course OWNER TO postgres;

--
-- Name: course_course_tags; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.course_course_tags (
    course_id uuid NOT NULL,
    course_tags_id uuid NOT NULL
);


ALTER TABLE public.course_course_tags OWNER TO postgres;

--
-- Name: course_tag; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.course_tag (
    id uuid NOT NULL,
    name character varying(255)
);


ALTER TABLE public.course_tag OWNER TO postgres;

--
-- Name: educator; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.educator (
    assigned_on timestamp(6) without time zone,
    assigned_by_id uuid,
    id uuid NOT NULL,
    user_id uuid,
    type character varying(255)
);


ALTER TABLE public.educator OWNER TO postgres;

--
-- Name: elemental_requirement; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.elemental_requirement (
    ordinal integer,
    id uuid NOT NULL,
    learning_requirement_id uuid,
    objective_text text,
    scientific_description text
);


ALTER TABLE public.elemental_requirement OWNER TO postgres;

--
-- Name: exercise_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.exercise_type (
    created_on timestamp without time zone NOT NULL,
    updated_on timestamp(6) without time zone,
    author_educator_id uuid,
    create_user_id uuid,
    id uuid NOT NULL,
    update_user_id uuid,
    description character varying(255),
    name character varying(255)
);


ALTER TABLE public.exercise_type OWNER TO postgres;

--
-- Name: exercise_type_report_template; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.exercise_type_report_template (
    exercise_type_id uuid NOT NULL,
    report_template_id uuid NOT NULL
);


ALTER TABLE public.exercise_type_report_template OWNER TO postgres;

--
-- Name: hint; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.hint (
    id uuid NOT NULL,
    text text
);


ALTER TABLE public.hint OWNER TO postgres;

--
-- Name: learning_requirement; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.learning_requirement (
    created_on timestamp without time zone NOT NULL,
    updated_on timestamp(6) without time zone,
    author_educator_id uuid,
    create_user_id uuid,
    id uuid NOT NULL,
    update_user_id uuid,
    knowledge_node_id character varying(255),
    name character varying(255)
);


ALTER TABLE public.learning_requirement OWNER TO postgres;

--
-- Name: learning_requirement_elemental_requirements; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.learning_requirement_elemental_requirements (
    elemental_requirements_id uuid NOT NULL,
    learning_requirement_id uuid NOT NULL
);


ALTER TABLE public.learning_requirement_elemental_requirements OWNER TO postgres;

--
-- Name: learning_resource; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.learning_resource (
    definition_type smallint,
    created_on timestamp without time zone NOT NULL,
    updated_on timestamp(6) without time zone,
    activity_id uuid,
    create_user_id uuid,
    definition_id uuid,
    id uuid NOT NULL,
    student_id uuid,
    update_user_id uuid,
    code text,
    visualisation_type character varying(255),
    CONSTRAINT learning_resource_definition_type_check CHECK (((definition_type >= 0) AND (definition_type <= 1)))
);


ALTER TABLE public.learning_resource OWNER TO postgres;

--
-- Name: learning_resource_definition; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.learning_resource_definition (
    created_on timestamp without time zone NOT NULL,
    updated_on timestamp(6) without time zone,
    activity_details_id uuid,
    author_educator_id uuid,
    create_user_id uuid,
    id uuid NOT NULL,
    theory_details_id uuid,
    update_user_id uuid
);


ALTER TABLE public.learning_resource_definition OWNER TO postgres;

--
-- Name: learning_resource_definition_learning_requirements; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.learning_resource_definition_learning_requirements (
    learning_requirements_id uuid NOT NULL,
    static_learning_resource_definition_id uuid NOT NULL
);


ALTER TABLE public.learning_resource_definition_learning_requirements OWNER TO postgres;

--
-- Name: learning_resource_qualified_requirements; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.learning_resource_qualified_requirements (
    learning_resource_id uuid NOT NULL,
    qualified_requirements_id uuid NOT NULL
);


ALTER TABLE public.learning_resource_qualified_requirements OWNER TO postgres;

--
-- Name: learning_resource_theory_cards; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.learning_resource_theory_cards (
    learning_resource_id uuid NOT NULL,
    theory_cards_id uuid NOT NULL
);


ALTER TABLE public.learning_resource_theory_cards OWNER TO postgres;

--
-- Name: learning_result; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.learning_result (
    created_on timestamp without time zone NOT NULL,
    updated_on timestamp(6) without time zone,
    create_user_id uuid,
    id uuid NOT NULL,
    solution_submission_id uuid,
    student_id uuid,
    update_user_id uuid,
    text text
);


ALTER TABLE public.learning_result OWNER TO postgres;

--
-- Name: learning_result_assessments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.learning_result_assessments (
    assessments_id uuid NOT NULL,
    learning_result_id uuid NOT NULL
);


ALTER TABLE public.learning_result_assessments OWNER TO postgres;

--
-- Name: lesson; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.lesson (
    created_on timestamp without time zone NOT NULL,
    updated_on timestamp(6) without time zone,
    author_educator_id uuid,
    course_id uuid,
    create_user_id uuid,
    id uuid NOT NULL,
    previous_element_id uuid,
    update_user_id uuid,
    description character varying(255),
    name character varying(255)
);


ALTER TABLE public.lesson OWNER TO postgres;

--
-- Name: report_template_paragraph; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.report_template_paragraph (
    ordinal integer NOT NULL,
    id uuid NOT NULL,
    text character varying(255),
    title character varying(255)
);


ALTER TABLE public.report_template_paragraph OWNER TO postgres;

--
-- Name: science; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.science (
    created_on timestamp without time zone NOT NULL,
    updated_on timestamp(6) without time zone,
    author_educator_id uuid,
    create_user_id uuid,
    id uuid NOT NULL,
    update_user_id uuid,
    description character varying(255),
    image_source character varying(255),
    name character varying(255)
);


ALTER TABLE public.science OWNER TO postgres;

--
-- Name: segment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.segment (
    created_on timestamp without time zone NOT NULL,
    updated_on timestamp(6) without time zone,
    author_educator_id uuid,
    create_user_id uuid,
    id uuid NOT NULL,
    learning_resource_definition_id uuid,
    lesson_id uuid,
    previous_element_id uuid,
    update_user_id uuid,
    name character varying(255),
    snippet_description text
);


ALTER TABLE public.segment OWNER TO postgres;

--
-- Name: solution_submission; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.solution_submission (
    hints_revealed integer NOT NULL,
    learning_resource_definition_type smallint,
    created_on timestamp without time zone NOT NULL,
    updated_on timestamp(6) without time zone,
    create_user_id uuid,
    id uuid NOT NULL,
    learning_resource_id uuid,
    student_id uuid,
    update_user_id uuid,
    report_text text,
    CONSTRAINT solution_submission_learning_resource_definition_type_check CHECK (((learning_resource_definition_type >= 0) AND (learning_resource_definition_type <= 1)))
);


ALTER TABLE public.solution_submission OWNER TO postgres;

--
-- Name: student; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.student (
    assigned_on timestamp(6) without time zone,
    id uuid NOT NULL,
    user_id uuid
);


ALTER TABLE public.student OWNER TO postgres;

--
-- Name: theory_card; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.theory_card (
    id uuid NOT NULL,
    identifier_value uuid,
    overview text
);


ALTER TABLE public.theory_card OWNER TO postgres;

--
-- Name: theory_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.theory_details (
    id uuid NOT NULL,
    graph_description text,
    overview_description text
);


ALTER TABLE public.theory_details OWNER TO postgres;

--
-- Name: activity_details activity_details_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.activity_details
    ADD CONSTRAINT activity_details_pkey PRIMARY KEY (id);


--
-- Name: activity_hints activity_hints_hints_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.activity_hints
    ADD CONSTRAINT activity_hints_hints_id_key UNIQUE (hints_id);


--
-- Name: activity_hints activity_hints_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.activity_hints
    ADD CONSTRAINT activity_hints_pkey PRIMARY KEY (activity_id, hints_id);


--
-- Name: activity activity_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.activity
    ADD CONSTRAINT activity_pkey PRIMARY KEY (id);


--
-- Name: administrator administrator_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.administrator
    ADD CONSTRAINT administrator_pkey PRIMARY KEY (id);


--
-- Name: assessment assessment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.assessment
    ADD CONSTRAINT assessment_pkey PRIMARY KEY (id);


--
-- Name: course_course_tags course_course_tags_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.course_course_tags
    ADD CONSTRAINT course_course_tags_pkey PRIMARY KEY (course_id, course_tags_id);


--
-- Name: course course_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.course
    ADD CONSTRAINT course_pkey PRIMARY KEY (id);


--
-- Name: course_tag course_tag_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.course_tag
    ADD CONSTRAINT course_tag_pkey PRIMARY KEY (id);


--
-- Name: educator educator_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.educator
    ADD CONSTRAINT educator_pkey PRIMARY KEY (id);


--
-- Name: elemental_requirement elemental_requirement_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.elemental_requirement
    ADD CONSTRAINT elemental_requirement_pkey PRIMARY KEY (id);


--
-- Name: exercise_type exercise_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exercise_type
    ADD CONSTRAINT exercise_type_pkey PRIMARY KEY (id);


--
-- Name: exercise_type_report_template exercise_type_report_template_report_template_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exercise_type_report_template
    ADD CONSTRAINT exercise_type_report_template_report_template_id_key UNIQUE (report_template_id);


--
-- Name: hint hint_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hint
    ADD CONSTRAINT hint_pkey PRIMARY KEY (id);


--
-- Name: learning_requirement_elemental_requirements learning_requirement_elemental_re_elemental_requirements_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_requirement_elemental_requirements
    ADD CONSTRAINT learning_requirement_elemental_re_elemental_requirements_id_key UNIQUE (elemental_requirements_id);


--
-- Name: learning_requirement learning_requirement_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_requirement
    ADD CONSTRAINT learning_requirement_pkey PRIMARY KEY (id);


--
-- Name: learning_resource learning_resource_activity_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_resource
    ADD CONSTRAINT learning_resource_activity_id_key UNIQUE (activity_id);


--
-- Name: learning_resource_definition learning_resource_definition_activity_details_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_resource_definition
    ADD CONSTRAINT learning_resource_definition_activity_details_id_key UNIQUE (activity_details_id);


--
-- Name: learning_resource_definition_learning_requirements learning_resource_definition_learning_requirements_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_resource_definition_learning_requirements
    ADD CONSTRAINT learning_resource_definition_learning_requirements_pkey PRIMARY KEY (learning_requirements_id, static_learning_resource_definition_id);


--
-- Name: learning_resource_definition learning_resource_definition_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_resource_definition
    ADD CONSTRAINT learning_resource_definition_pkey PRIMARY KEY (id);


--
-- Name: learning_resource_definition learning_resource_definition_theory_details_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_resource_definition
    ADD CONSTRAINT learning_resource_definition_theory_details_id_key UNIQUE (theory_details_id);


--
-- Name: learning_resource learning_resource_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_resource
    ADD CONSTRAINT learning_resource_pkey PRIMARY KEY (id);


--
-- Name: learning_resource_qualified_requirements learning_resource_qualified_requirements_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_resource_qualified_requirements
    ADD CONSTRAINT learning_resource_qualified_requirements_pkey PRIMARY KEY (learning_resource_id, qualified_requirements_id);


--
-- Name: learning_resource_theory_cards learning_resource_theory_cards_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_resource_theory_cards
    ADD CONSTRAINT learning_resource_theory_cards_pkey PRIMARY KEY (learning_resource_id, theory_cards_id);


--
-- Name: learning_resource_theory_cards learning_resource_theory_cards_theory_cards_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_resource_theory_cards
    ADD CONSTRAINT learning_resource_theory_cards_theory_cards_id_key UNIQUE (theory_cards_id);


--
-- Name: learning_result_assessments learning_result_assessments_assessments_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_result_assessments
    ADD CONSTRAINT learning_result_assessments_assessments_id_key UNIQUE (assessments_id);


--
-- Name: learning_result_assessments learning_result_assessments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_result_assessments
    ADD CONSTRAINT learning_result_assessments_pkey PRIMARY KEY (assessments_id, learning_result_id);


--
-- Name: learning_result learning_result_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_result
    ADD CONSTRAINT learning_result_pkey PRIMARY KEY (id);


--
-- Name: lesson lesson_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lesson
    ADD CONSTRAINT lesson_pkey PRIMARY KEY (id);


--
-- Name: report_template_paragraph report_template_paragraph_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.report_template_paragraph
    ADD CONSTRAINT report_template_paragraph_pkey PRIMARY KEY (id);


--
-- Name: science science_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.science
    ADD CONSTRAINT science_pkey PRIMARY KEY (id);


--
-- Name: segment segment_learning_resource_definition_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.segment
    ADD CONSTRAINT segment_learning_resource_definition_id_key UNIQUE (learning_resource_definition_id);


--
-- Name: segment segment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.segment
    ADD CONSTRAINT segment_pkey PRIMARY KEY (id);


--
-- Name: solution_submission solution_submission_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.solution_submission
    ADD CONSTRAINT solution_submission_pkey PRIMARY KEY (id);


--
-- Name: student student_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_pkey PRIMARY KEY (id);


--
-- Name: theory_card theory_card_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.theory_card
    ADD CONSTRAINT theory_card_pkey PRIMARY KEY (id);


--
-- Name: theory_details theory_details_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.theory_details
    ADD CONSTRAINT theory_details_pkey PRIMARY KEY (id);


--
-- Name: exercise_type_report_template fk1hfa8jw85wrhjkbxpn6s8y2u0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exercise_type_report_template
    ADD CONSTRAINT fk1hfa8jw85wrhjkbxpn6s8y2u0 FOREIGN KEY (exercise_type_id) REFERENCES public.exercise_type(id);


--
-- Name: learning_requirement_elemental_requirements fk2755ei4aamf1bw1x511renqcw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_requirement_elemental_requirements
    ADD CONSTRAINT fk2755ei4aamf1bw1x511renqcw FOREIGN KEY (elemental_requirements_id) REFERENCES public.elemental_requirement(id);


--
-- Name: learning_resource_qualified_requirements fk2chwglp1h8q1bejfyhoxd30ek; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_resource_qualified_requirements
    ADD CONSTRAINT fk2chwglp1h8q1bejfyhoxd30ek FOREIGN KEY (qualified_requirements_id) REFERENCES public.elemental_requirement(id);


--
-- Name: learning_requirement_elemental_requirements fk2mjao55qv09ki1yngaeyugyc5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_requirement_elemental_requirements
    ADD CONSTRAINT fk2mjao55qv09ki1yngaeyugyc5 FOREIGN KEY (learning_requirement_id) REFERENCES public.learning_requirement(id);


--
-- Name: course fk2yjh6o4cjs5n6o7o7o1rxhaef; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.course
    ADD CONSTRAINT fk2yjh6o4cjs5n6o7o7o1rxhaef FOREIGN KEY (science_id) REFERENCES public.science(id);


--
-- Name: exercise_type_report_template fk330g7xu4hf7ublwmb6lwewvux; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exercise_type_report_template
    ADD CONSTRAINT fk330g7xu4hf7ublwmb6lwewvux FOREIGN KEY (report_template_id) REFERENCES public.report_template_paragraph(id);


--
-- Name: lesson fk3l58cluibkxs8gej4hil0n4ay; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lesson
    ADD CONSTRAINT fk3l58cluibkxs8gej4hil0n4ay FOREIGN KEY (previous_element_id) REFERENCES public.lesson(id);


--
-- Name: assessment_qualified_elemental_requirements fk3s35qnskw8a7wg5rncadusku1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.assessment_qualified_elemental_requirements
    ADD CONSTRAINT fk3s35qnskw8a7wg5rncadusku1 FOREIGN KEY (qualified_elemental_requirements_id) REFERENCES public.elemental_requirement(id);


--
-- Name: learning_resource_qualified_requirements fk5h0wfw743182ry92yxa4dpvw2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_resource_qualified_requirements
    ADD CONSTRAINT fk5h0wfw743182ry92yxa4dpvw2 FOREIGN KEY (learning_resource_id) REFERENCES public.learning_resource(id);


--
-- Name: exercise_type fk5lcmm7vgxxxl90i9y32t1iu64; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exercise_type
    ADD CONSTRAINT fk5lcmm7vgxxxl90i9y32t1iu64 FOREIGN KEY (author_educator_id) REFERENCES public.educator(id);


--
-- Name: segment fk5qftegka2u5smv72m45hak0qi; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.segment
    ADD CONSTRAINT fk5qftegka2u5smv72m45hak0qi FOREIGN KEY (previous_element_id) REFERENCES public.segment(id);


--
-- Name: segment fk5rqpv4vb19454qiw7guk5tmga; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.segment
    ADD CONSTRAINT fk5rqpv4vb19454qiw7guk5tmga FOREIGN KEY (lesson_id) REFERENCES public.lesson(id);


--
-- Name: course fk61ut3ukvnxp2bq5r59k5okv6a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.course
    ADD CONSTRAINT fk61ut3ukvnxp2bq5r59k5okv6a FOREIGN KEY (author_educator_id) REFERENCES public.educator(id);


--
-- Name: learning_resource_definition_learning_requirements fk6bm96r6pt1nvtqqba730ihcwf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_resource_definition_learning_requirements
    ADD CONSTRAINT fk6bm96r6pt1nvtqqba730ihcwf FOREIGN KEY (static_learning_resource_definition_id) REFERENCES public.learning_resource_definition(id);


--
-- Name: learning_requirement fk6hdbb3vnu9xwq8sl4239mbp51; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_requirement
    ADD CONSTRAINT fk6hdbb3vnu9xwq8sl4239mbp51 FOREIGN KEY (author_educator_id) REFERENCES public.educator(id);


--
-- Name: course_course_tags fk6xehrkmpiiacfk11us93xin0b; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.course_course_tags
    ADD CONSTRAINT fk6xehrkmpiiacfk11us93xin0b FOREIGN KEY (course_tags_id) REFERENCES public.course_tag(id);


--
-- Name: learning_resource_definition_learning_requirements fk85qssv8nr7195u7f8edsv0qrm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_resource_definition_learning_requirements
    ADD CONSTRAINT fk85qssv8nr7195u7f8edsv0qrm FOREIGN KEY (learning_requirements_id) REFERENCES public.learning_requirement(id);


--
-- Name: learning_resource_definition fk8vwtwo2c083e27u3xpsi8jb2n; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_resource_definition
    ADD CONSTRAINT fk8vwtwo2c083e27u3xpsi8jb2n FOREIGN KEY (theory_details_id) REFERENCES public.theory_details(id);


--
-- Name: learning_result fk99md3bitcecgnwjn3m3lr2ic; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_result
    ADD CONSTRAINT fk99md3bitcecgnwjn3m3lr2ic FOREIGN KEY (solution_submission_id) REFERENCES public.solution_submission(id);


--
-- Name: science fk9kq3n78kpk70q5ya76h467gk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.science
    ADD CONSTRAINT fk9kq3n78kpk70q5ya76h467gk2 FOREIGN KEY (author_educator_id) REFERENCES public.educator(id);


--
-- Name: assessment_qualified_elemental_requirements fkcp7ymcapuo7ff8a0udjlmrj8x; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.assessment_qualified_elemental_requirements
    ADD CONSTRAINT fkcp7ymcapuo7ff8a0udjlmrj8x FOREIGN KEY (assessment_id) REFERENCES public.assessment(id);


--
-- Name: learning_resource_definition fkec7u1fy6a5d3e1k1i1m8e8lae; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_resource_definition
    ADD CONSTRAINT fkec7u1fy6a5d3e1k1i1m8e8lae FOREIGN KEY (activity_details_id) REFERENCES public.activity_details(id);


--
-- Name: learning_resource_theory_cards fkesrtnbl5nsgidphlxerqrsv3f; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_resource_theory_cards
    ADD CONSTRAINT fkesrtnbl5nsgidphlxerqrsv3f FOREIGN KEY (learning_resource_id) REFERENCES public.learning_resource(id);


--
-- Name: learning_result_assessments fkfedbkllcx2hgi1itp7yiw2i1l; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_result_assessments
    ADD CONSTRAINT fkfedbkllcx2hgi1itp7yiw2i1l FOREIGN KEY (assessments_id) REFERENCES public.assessment(id);


--
-- Name: learning_result_assessments fkgsasek8c08x610flxify3vr5o; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_result_assessments
    ADD CONSTRAINT fkgsasek8c08x610flxify3vr5o FOREIGN KEY (learning_result_id) REFERENCES public.learning_result(id);


--
-- Name: learning_resource fkhb9xxta8y6d0chybwr2lsmabt; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_resource
    ADD CONSTRAINT fkhb9xxta8y6d0chybwr2lsmabt FOREIGN KEY (activity_id) REFERENCES public.activity(id);


--
-- Name: elemental_requirement fkhiyoe51ha01elim3uubwkrk0s; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.elemental_requirement
    ADD CONSTRAINT fkhiyoe51ha01elim3uubwkrk0s FOREIGN KEY (learning_requirement_id) REFERENCES public.learning_requirement(id);


--
-- Name: activity_hints fkiy9bnf9085j0xg0w5oe7f8kmv; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.activity_hints
    ADD CONSTRAINT fkiy9bnf9085j0xg0w5oe7f8kmv FOREIGN KEY (activity_id) REFERENCES public.activity(id);


--
-- Name: learning_resource_definition fkiycqlowihkq03gkpl51guusf7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_resource_definition
    ADD CONSTRAINT fkiycqlowihkq03gkpl51guusf7 FOREIGN KEY (author_educator_id) REFERENCES public.educator(id);


--
-- Name: educator fkje49wygblbkx6qxrd75kh2pgi; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.educator
    ADD CONSTRAINT fkje49wygblbkx6qxrd75kh2pgi FOREIGN KEY (assigned_by_id) REFERENCES public.administrator(id);


--
-- Name: lesson fkjs3c7skmg8bvdddok5lc7s807; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lesson
    ADD CONSTRAINT fkjs3c7skmg8bvdddok5lc7s807 FOREIGN KEY (course_id) REFERENCES public.course(id);


--
-- Name: solution_submission fkjsdciptbcht9aqov2s2hba0i3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.solution_submission
    ADD CONSTRAINT fkjsdciptbcht9aqov2s2hba0i3 FOREIGN KEY (student_id) REFERENCES public.student(id);


--
-- Name: segment fkm6p3ifkln6d2vyoirbugg37rl; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.segment
    ADD CONSTRAINT fkm6p3ifkln6d2vyoirbugg37rl FOREIGN KEY (author_educator_id) REFERENCES public.educator(id);


--
-- Name: activity_hints fknfiel0rrv7ixcl6t9mbyp16g4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.activity_hints
    ADD CONSTRAINT fknfiel0rrv7ixcl6t9mbyp16g4 FOREIGN KEY (hints_id) REFERENCES public.hint(id);


--
-- Name: learning_result fknx4wj0pyox2ap78ekhwk8y008; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_result
    ADD CONSTRAINT fknx4wj0pyox2ap78ekhwk8y008 FOREIGN KEY (student_id) REFERENCES public.student(id);


--
-- Name: lesson fkqpk6k3fnk7biw100oy01otawl; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lesson
    ADD CONSTRAINT fkqpk6k3fnk7biw100oy01otawl FOREIGN KEY (author_educator_id) REFERENCES public.educator(id);


--
-- Name: course_course_tags fksgk9yam7jk6ij2v7duscny5ob; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.course_course_tags
    ADD CONSTRAINT fksgk9yam7jk6ij2v7duscny5ob FOREIGN KEY (course_id) REFERENCES public.course(id);


--
-- Name: learning_resource_theory_cards fksr8xjtk1hb49ejdgtkeukk34o; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_resource_theory_cards
    ADD CONSTRAINT fksr8xjtk1hb49ejdgtkeukk34o FOREIGN KEY (theory_cards_id) REFERENCES public.theory_card(id);


--
-- PostgreSQL database dump complete
--

