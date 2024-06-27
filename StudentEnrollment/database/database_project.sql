--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3
-- Dumped by pg_dump version 16.3

-- Started on 2024-06-27 21:11:05

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
-- TOC entry 218 (class 1259 OID 16468)
-- Name: classroom; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.classroom (
    classroomid integer NOT NULL,
    roomnr character varying(10) NOT NULL,
    building character varying(50) NOT NULL,
    capacity integer NOT NULL,
    CONSTRAINT classroom_capacity_check CHECK ((capacity >= 0))
);


ALTER TABLE public.classroom OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16467)
-- Name: classroom_classroomid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.classroom_classroomid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.classroom_classroomid_seq OWNER TO postgres;

--
-- TOC entry 4942 (class 0 OID 0)
-- Dependencies: 217
-- Name: classroom_classroomid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.classroom_classroomid_seq OWNED BY public.classroom.classroomid;


--
-- TOC entry 226 (class 1259 OID 16624)
-- Name: covers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.covers (
    examid integer NOT NULL,
    subjectid integer NOT NULL
);


ALTER TABLE public.covers OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16492)
-- Name: exam; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.exam (
    examid integer NOT NULL,
    examdate date NOT NULL,
    professorid integer,
    subjectid integer,
    classroomid integer
);


ALTER TABLE public.exam OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16491)
-- Name: exam_examid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.exam_examid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.exam_examid_seq OWNER TO postgres;

--
-- TOC entry 4943 (class 0 OID 0)
-- Dependencies: 223
-- Name: exam_examid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.exam_examid_seq OWNED BY public.exam.examid;


--
-- TOC entry 227 (class 1259 OID 16639)
-- Name: heldin; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.heldin (
    examid integer NOT NULL,
    classroomid integer NOT NULL
);


ALTER TABLE public.heldin OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 16654)
-- Name: hosts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.hosts (
    classroomid integer NOT NULL,
    subjectid integer NOT NULL
);


ALTER TABLE public.hosts OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 16684)
-- Name: mentors; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mentors (
    professorid integer NOT NULL,
    studentid integer NOT NULL
);


ALTER TABLE public.mentors OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16483)
-- Name: professor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.professor (
    professorid integer NOT NULL,
    firstname character varying(50) NOT NULL,
    lastname character varying(50) NOT NULL,
    email character varying(100),
    phonenumbers text,
    department character varying(100),
    affiliation character varying(100)
);


ALTER TABLE public.professor OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16482)
-- Name: professor_professorid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.professor_professorid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.professor_professorid_seq OWNER TO postgres;

--
-- TOC entry 4944 (class 0 OID 0)
-- Dependencies: 221
-- Name: professor_professorid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.professor_professorid_seq OWNED BY public.professor.professorid;


--
-- TOC entry 216 (class 1259 OID 16459)
-- Name: student; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.student (
    studentid integer NOT NULL,
    firstname character varying(50) NOT NULL,
    lastname character varying(50) NOT NULL,
    dateofbirth date NOT NULL,
    email character varying(100),
    work character varying(255),
    level character varying(50)
);


ALTER TABLE public.student OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16458)
-- Name: student_studentid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.student_studentid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.student_studentid_seq OWNER TO postgres;

--
-- TOC entry 4945 (class 0 OID 0)
-- Dependencies: 215
-- Name: student_studentid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.student_studentid_seq OWNED BY public.student.studentid;


--
-- TOC entry 220 (class 1259 OID 16476)
-- Name: subject; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.subject (
    subjectid integer NOT NULL,
    subjectname character varying(50) NOT NULL
);


ALTER TABLE public.subject OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16475)
-- Name: subject_subjectid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.subject_subjectid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.subject_subjectid_seq OWNER TO postgres;

--
-- TOC entry 4946 (class 0 OID 0)
-- Dependencies: 219
-- Name: subject_subjectid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.subject_subjectid_seq OWNED BY public.subject.subjectid;


--
-- TOC entry 225 (class 1259 OID 16609)
-- Name: takes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.takes (
    studentid integer NOT NULL,
    examid integer NOT NULL
);


ALTER TABLE public.takes OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 16669)
-- Name: taughtby; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.taughtby (
    subjectid integer NOT NULL,
    professorid integer NOT NULL
);


ALTER TABLE public.taughtby OWNER TO postgres;

--
-- TOC entry 4733 (class 2604 OID 16471)
-- Name: classroom classroomid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.classroom ALTER COLUMN classroomid SET DEFAULT nextval('public.classroom_classroomid_seq'::regclass);


--
-- TOC entry 4736 (class 2604 OID 16495)
-- Name: exam examid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exam ALTER COLUMN examid SET DEFAULT nextval('public.exam_examid_seq'::regclass);


--
-- TOC entry 4735 (class 2604 OID 16486)
-- Name: professor professorid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.professor ALTER COLUMN professorid SET DEFAULT nextval('public.professor_professorid_seq'::regclass);


--
-- TOC entry 4732 (class 2604 OID 16462)
-- Name: student studentid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student ALTER COLUMN studentid SET DEFAULT nextval('public.student_studentid_seq'::regclass);


--
-- TOC entry 4734 (class 2604 OID 16479)
-- Name: subject subjectid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subject ALTER COLUMN subjectid SET DEFAULT nextval('public.subject_subjectid_seq'::regclass);


--
-- TOC entry 4924 (class 0 OID 16468)
-- Dependencies: 218
-- Data for Name: classroom; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.classroom (classroomid, roomnr, building, capacity) FROM stdin;
87122	03	B	1145
\.


--
-- TOC entry 4932 (class 0 OID 16624)
-- Dependencies: 226
-- Data for Name: covers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.covers (examid, subjectid) FROM stdin;
\.


--
-- TOC entry 4930 (class 0 OID 16492)
-- Dependencies: 224
-- Data for Name: exam; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.exam (examid, examdate, professorid, subjectid, classroomid) FROM stdin;
1012832	2024-11-11	\N	\N	\N
\.


--
-- TOC entry 4933 (class 0 OID 16639)
-- Dependencies: 227
-- Data for Name: heldin; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.heldin (examid, classroomid) FROM stdin;
\.


--
-- TOC entry 4934 (class 0 OID 16654)
-- Dependencies: 228
-- Data for Name: hosts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.hosts (classroomid, subjectid) FROM stdin;
\.


--
-- TOC entry 4936 (class 0 OID 16684)
-- Dependencies: 230
-- Data for Name: mentors; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.mentors (professorid, studentid) FROM stdin;
232006	252002
\.


--
-- TOC entry 4928 (class 0 OID 16483)
-- Dependencies: 222
-- Data for Name: professor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.professor (professorid, firstname, lastname, email, phonenumbers, department, affiliation) FROM stdin;
232006	Alessandro	Artale				
\.


--
-- TOC entry 4922 (class 0 OID 16459)
-- Dependencies: 216
-- Data for Name: student; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.student (studentid, firstname, lastname, dateofbirth, email, work, level) FROM stdin;
252002	Ana	Lena	2002-01-01	\N	\N	BSc
\.


--
-- TOC entry 4926 (class 0 OID 16476)
-- Dependencies: 220
-- Data for Name: subject; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.subject (subjectid, subjectname) FROM stdin;
119	Mathematics and logic
\.


--
-- TOC entry 4931 (class 0 OID 16609)
-- Dependencies: 225
-- Data for Name: takes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.takes (studentid, examid) FROM stdin;
\.


--
-- TOC entry 4935 (class 0 OID 16669)
-- Dependencies: 229
-- Data for Name: taughtby; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.taughtby (subjectid, professorid) FROM stdin;
119	232006
\.


--
-- TOC entry 4947 (class 0 OID 0)
-- Dependencies: 217
-- Name: classroom_classroomid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.classroom_classroomid_seq', 1, false);


--
-- TOC entry 4948 (class 0 OID 0)
-- Dependencies: 223
-- Name: exam_examid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.exam_examid_seq', 1, false);


--
-- TOC entry 4949 (class 0 OID 0)
-- Dependencies: 221
-- Name: professor_professorid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.professor_professorid_seq', 35, true);


--
-- TOC entry 4950 (class 0 OID 0)
-- Dependencies: 215
-- Name: student_studentid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.student_studentid_seq', 56, true);


--
-- TOC entry 4951 (class 0 OID 0)
-- Dependencies: 219
-- Name: subject_subjectid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.subject_subjectid_seq', 1, false);


--
-- TOC entry 4741 (class 2606 OID 16474)
-- Name: classroom classroom_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.classroom
    ADD CONSTRAINT classroom_pkey PRIMARY KEY (classroomid);


--
-- TOC entry 4751 (class 2606 OID 16628)
-- Name: covers covers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.covers
    ADD CONSTRAINT covers_pkey PRIMARY KEY (examid, subjectid);


--
-- TOC entry 4747 (class 2606 OID 16497)
-- Name: exam exam_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exam
    ADD CONSTRAINT exam_pkey PRIMARY KEY (examid);


--
-- TOC entry 4753 (class 2606 OID 16643)
-- Name: heldin heldin_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.heldin
    ADD CONSTRAINT heldin_pkey PRIMARY KEY (examid, classroomid);


--
-- TOC entry 4757 (class 2606 OID 16658)
-- Name: hosts hosts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hosts
    ADD CONSTRAINT hosts_pkey PRIMARY KEY (classroomid, subjectid);


--
-- TOC entry 4761 (class 2606 OID 16688)
-- Name: mentors mentors_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mentors
    ADD CONSTRAINT mentors_pkey PRIMARY KEY (professorid, studentid);


--
-- TOC entry 4745 (class 2606 OID 16490)
-- Name: professor professor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.professor
    ADD CONSTRAINT professor_pkey PRIMARY KEY (professorid);


--
-- TOC entry 4739 (class 2606 OID 16466)
-- Name: student student_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_pkey PRIMARY KEY (studentid);


--
-- TOC entry 4743 (class 2606 OID 16481)
-- Name: subject subject_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subject
    ADD CONSTRAINT subject_pkey PRIMARY KEY (subjectid);


--
-- TOC entry 4749 (class 2606 OID 16613)
-- Name: takes takes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.takes
    ADD CONSTRAINT takes_pkey PRIMARY KEY (studentid, examid);


--
-- TOC entry 4759 (class 2606 OID 16673)
-- Name: taughtby taughtby_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.taughtby
    ADD CONSTRAINT taughtby_pkey PRIMARY KEY (subjectid, professorid);


--
-- TOC entry 4755 (class 2606 OID 16700)
-- Name: heldin unique_exam_schedule; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.heldin
    ADD CONSTRAINT unique_exam_schedule UNIQUE (examid, classroomid);


--
-- TOC entry 4768 (class 2606 OID 16629)
-- Name: covers covers_examid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.covers
    ADD CONSTRAINT covers_examid_fkey FOREIGN KEY (examid) REFERENCES public.exam(examid);


--
-- TOC entry 4769 (class 2606 OID 16634)
-- Name: covers covers_subjectid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.covers
    ADD CONSTRAINT covers_subjectid_fkey FOREIGN KEY (subjectid) REFERENCES public.subject(subjectid);


--
-- TOC entry 4762 (class 2606 OID 16604)
-- Name: exam exam_classroomid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exam
    ADD CONSTRAINT exam_classroomid_fkey FOREIGN KEY (classroomid) REFERENCES public.classroom(classroomid);


--
-- TOC entry 4763 (class 2606 OID 16594)
-- Name: exam exam_professorid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exam
    ADD CONSTRAINT exam_professorid_fkey FOREIGN KEY (professorid) REFERENCES public.professor(professorid);


--
-- TOC entry 4764 (class 2606 OID 16599)
-- Name: exam exam_subjectid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exam
    ADD CONSTRAINT exam_subjectid_fkey FOREIGN KEY (subjectid) REFERENCES public.subject(subjectid);


--
-- TOC entry 4770 (class 2606 OID 16649)
-- Name: heldin heldin_classroomid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.heldin
    ADD CONSTRAINT heldin_classroomid_fkey FOREIGN KEY (classroomid) REFERENCES public.classroom(classroomid);


--
-- TOC entry 4771 (class 2606 OID 16644)
-- Name: heldin heldin_examid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.heldin
    ADD CONSTRAINT heldin_examid_fkey FOREIGN KEY (examid) REFERENCES public.exam(examid);


--
-- TOC entry 4772 (class 2606 OID 16659)
-- Name: hosts hosts_classroomid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hosts
    ADD CONSTRAINT hosts_classroomid_fkey FOREIGN KEY (classroomid) REFERENCES public.classroom(classroomid);


--
-- TOC entry 4773 (class 2606 OID 16664)
-- Name: hosts hosts_subjectid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hosts
    ADD CONSTRAINT hosts_subjectid_fkey FOREIGN KEY (subjectid) REFERENCES public.subject(subjectid);


--
-- TOC entry 4776 (class 2606 OID 16689)
-- Name: mentors mentors_professorid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mentors
    ADD CONSTRAINT mentors_professorid_fkey FOREIGN KEY (professorid) REFERENCES public.professor(professorid);


--
-- TOC entry 4777 (class 2606 OID 16694)
-- Name: mentors mentors_studentid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mentors
    ADD CONSTRAINT mentors_studentid_fkey FOREIGN KEY (studentid) REFERENCES public.student(studentid);


--
-- TOC entry 4765 (class 2606 OID 16701)
-- Name: exam professor_organizes_own_exam; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exam
    ADD CONSTRAINT professor_organizes_own_exam FOREIGN KEY (professorid, subjectid) REFERENCES public.taughtby(professorid, subjectid);


--
-- TOC entry 4766 (class 2606 OID 16619)
-- Name: takes takes_examid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.takes
    ADD CONSTRAINT takes_examid_fkey FOREIGN KEY (examid) REFERENCES public.exam(examid);


--
-- TOC entry 4767 (class 2606 OID 16614)
-- Name: takes takes_studentid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.takes
    ADD CONSTRAINT takes_studentid_fkey FOREIGN KEY (studentid) REFERENCES public.student(studentid);


--
-- TOC entry 4774 (class 2606 OID 16679)
-- Name: taughtby taughtby_professorid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.taughtby
    ADD CONSTRAINT taughtby_professorid_fkey FOREIGN KEY (professorid) REFERENCES public.professor(professorid);


--
-- TOC entry 4775 (class 2606 OID 16674)
-- Name: taughtby taughtby_subjectid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.taughtby
    ADD CONSTRAINT taughtby_subjectid_fkey FOREIGN KEY (subjectid) REFERENCES public.subject(subjectid);


-- Completed on 2024-06-27 21:11:05

--
-- PostgreSQL database dump complete
--

