PGDMP      5                |           StudentEnrollmentDB    16.3    16.3 E    K           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            L           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            M           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            N           1262    16457    StudentEnrollmentDB    DATABASE     �   CREATE DATABASE "StudentEnrollmentDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
 %   DROP DATABASE "StudentEnrollmentDB";
                postgres    false            �            1259    16778 	   classroom    TABLE     �   CREATE TABLE public.classroom (
    roomnr character varying(10) NOT NULL,
    building character varying(50) NOT NULL,
    capacity integer NOT NULL,
    CONSTRAINT classroom_capacity_check CHECK ((capacity >= 0))
);
    DROP TABLE public.classroom;
       public         heap    postgres    false            �            1259    16624    covers    TABLE     \   CREATE TABLE public.covers (
    examid integer NOT NULL,
    subjectid integer NOT NULL
);
    DROP TABLE public.covers;
       public         heap    postgres    false            �            1259    16792    exam    TABLE     T  CREATE TABLE public.exam (
    examid integer NOT NULL,
    examdate date NOT NULL,
    examstarttime time without time zone NOT NULL,
    examendtime time without time zone NOT NULL,
    professorid integer NOT NULL,
    subjectid integer NOT NULL,
    roomnr character varying(10) NOT NULL,
    building character varying(50) NOT NULL
);
    DROP TABLE public.exam;
       public         heap    postgres    false            �            1259    16791    exam_examid_seq    SEQUENCE     �   CREATE SEQUENCE public.exam_examid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.exam_examid_seq;
       public          postgres    false    226            O           0    0    exam_examid_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.exam_examid_seq OWNED BY public.exam.examid;
          public          postgres    false    225            �            1259    16639    heldin    TABLE     ^   CREATE TABLE public.heldin (
    examid integer NOT NULL,
    classroomid integer NOT NULL
);
    DROP TABLE public.heldin;
       public         heap    postgres    false            �            1259    16843    hosts    TABLE     �   CREATE TABLE public.hosts (
    roomnr character varying(10) NOT NULL,
    building character varying(50) NOT NULL,
    subjectid integer NOT NULL
);
    DROP TABLE public.hosts;
       public         heap    postgres    false            �            1259    16858    mentors    TABLE     b   CREATE TABLE public.mentors (
    professorid integer NOT NULL,
    studentid integer NOT NULL
);
    DROP TABLE public.mentors;
       public         heap    postgres    false            �            1259    16768    phone    TABLE     k   CREATE TABLE public.phone (
    number character varying(15) NOT NULL,
    professorid integer NOT NULL
);
    DROP TABLE public.phone;
       public         heap    postgres    false            �            1259    16762 	   professor    TABLE       CREATE TABLE public.professor (
    professorid integer NOT NULL,
    firstname character varying(50) NOT NULL,
    lastname character varying(50) NOT NULL,
    email character varying(100),
    department character varying(100),
    affiliation character varying(100)
);
    DROP TABLE public.professor;
       public         heap    postgres    false            �            1259    16761    professor_professorid_seq    SEQUENCE     �   CREATE SEQUENCE public.professor_professorid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.professor_professorid_seq;
       public          postgres    false    220            P           0    0    professor_professorid_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.professor_professorid_seq OWNED BY public.professor.professorid;
          public          postgres    false    219            �            1259    16753    student    TABLE     '  CREATE TABLE public.student (
    studentid integer NOT NULL,
    firstname character varying(50) NOT NULL,
    lastname character varying(50) NOT NULL,
    dateofbirth date NOT NULL,
    email character varying(100),
    level character varying(50) NOT NULL,
    work character varying(255)
);
    DROP TABLE public.student;
       public         heap    postgres    false            �            1259    16752    student_studentid_seq    SEQUENCE     �   CREATE SEQUENCE public.student_studentid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.student_studentid_seq;
       public          postgres    false    218            Q           0    0    student_studentid_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.student_studentid_seq OWNED BY public.student.studentid;
          public          postgres    false    217            �            1259    16785    subject    TABLE     p   CREATE TABLE public.subject (
    subjectid integer NOT NULL,
    subjectname character varying(50) NOT NULL
);
    DROP TABLE public.subject;
       public         heap    postgres    false            �            1259    16784    subject_subjectid_seq    SEQUENCE     �   CREATE SEQUENCE public.subject_subjectid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.subject_subjectid_seq;
       public          postgres    false    224            R           0    0    subject_subjectid_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.subject_subjectid_seq OWNED BY public.subject.subjectid;
          public          postgres    false    223            �            1259    16828    takes    TABLE     [   CREATE TABLE public.takes (
    studentid integer NOT NULL,
    examid integer NOT NULL
);
    DROP TABLE public.takes;
       public         heap    postgres    false            �            1259    16813    taughtby    TABLE     c   CREATE TABLE public.taughtby (
    subjectid integer NOT NULL,
    professorid integer NOT NULL
);
    DROP TABLE public.taughtby;
       public         heap    postgres    false            �           2604    16795    exam examid    DEFAULT     j   ALTER TABLE ONLY public.exam ALTER COLUMN examid SET DEFAULT nextval('public.exam_examid_seq'::regclass);
 :   ALTER TABLE public.exam ALTER COLUMN examid DROP DEFAULT;
       public          postgres    false    225    226    226            �           2604    16765    professor professorid    DEFAULT     ~   ALTER TABLE ONLY public.professor ALTER COLUMN professorid SET DEFAULT nextval('public.professor_professorid_seq'::regclass);
 D   ALTER TABLE public.professor ALTER COLUMN professorid DROP DEFAULT;
       public          postgres    false    220    219    220                       2604    16756    student studentid    DEFAULT     v   ALTER TABLE ONLY public.student ALTER COLUMN studentid SET DEFAULT nextval('public.student_studentid_seq'::regclass);
 @   ALTER TABLE public.student ALTER COLUMN studentid DROP DEFAULT;
       public          postgres    false    218    217    218            �           2604    16788    subject subjectid    DEFAULT     v   ALTER TABLE ONLY public.subject ALTER COLUMN subjectid SET DEFAULT nextval('public.subject_subjectid_seq'::regclass);
 @   ALTER TABLE public.subject ALTER COLUMN subjectid DROP DEFAULT;
       public          postgres    false    223    224    224            @          0    16778 	   classroom 
   TABLE DATA           ?   COPY public.classroom (roomnr, building, capacity) FROM stdin;
    public          postgres    false    222   �P       9          0    16624    covers 
   TABLE DATA           3   COPY public.covers (examid, subjectid) FROM stdin;
    public          postgres    false    215   �P       D          0    16792    exam 
   TABLE DATA           v   COPY public.exam (examid, examdate, examstarttime, examendtime, professorid, subjectid, roomnr, building) FROM stdin;
    public          postgres    false    226   !Q       :          0    16639    heldin 
   TABLE DATA           5   COPY public.heldin (examid, classroomid) FROM stdin;
    public          postgres    false    216   �Q       G          0    16843    hosts 
   TABLE DATA           <   COPY public.hosts (roomnr, building, subjectid) FROM stdin;
    public          postgres    false    229   �Q       H          0    16858    mentors 
   TABLE DATA           9   COPY public.mentors (professorid, studentid) FROM stdin;
    public          postgres    false    230   �Q       ?          0    16768    phone 
   TABLE DATA           4   COPY public.phone (number, professorid) FROM stdin;
    public          postgres    false    221   #R       >          0    16762 	   professor 
   TABLE DATA           e   COPY public.professor (professorid, firstname, lastname, email, department, affiliation) FROM stdin;
    public          postgres    false    220   @R       <          0    16753    student 
   TABLE DATA           b   COPY public.student (studentid, firstname, lastname, dateofbirth, email, level, work) FROM stdin;
    public          postgres    false    218   �R       B          0    16785    subject 
   TABLE DATA           9   COPY public.subject (subjectid, subjectname) FROM stdin;
    public          postgres    false    224   S       F          0    16828    takes 
   TABLE DATA           2   COPY public.takes (studentid, examid) FROM stdin;
    public          postgres    false    228   �S       E          0    16813    taughtby 
   TABLE DATA           :   COPY public.taughtby (subjectid, professorid) FROM stdin;
    public          postgres    false    227   �S       S           0    0    exam_examid_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.exam_examid_seq', 1, false);
          public          postgres    false    225            T           0    0    professor_professorid_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.professor_professorid_seq', 1, false);
          public          postgres    false    219            U           0    0    student_studentid_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.student_studentid_seq', 1, false);
          public          postgres    false    217            V           0    0    subject_subjectid_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.subject_subjectid_seq', 1, false);
          public          postgres    false    223            �           2606    16783    classroom classroom_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.classroom
    ADD CONSTRAINT classroom_pkey PRIMARY KEY (roomnr, building);
 B   ALTER TABLE ONLY public.classroom DROP CONSTRAINT classroom_pkey;
       public            postgres    false    222    222            �           2606    16628    covers covers_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.covers
    ADD CONSTRAINT covers_pkey PRIMARY KEY (examid, subjectid);
 <   ALTER TABLE ONLY public.covers DROP CONSTRAINT covers_pkey;
       public            postgres    false    215    215            �           2606    16797    exam exam_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.exam
    ADD CONSTRAINT exam_pkey PRIMARY KEY (examid);
 8   ALTER TABLE ONLY public.exam DROP CONSTRAINT exam_pkey;
       public            postgres    false    226            �           2606    16643    heldin heldin_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY public.heldin
    ADD CONSTRAINT heldin_pkey PRIMARY KEY (examid, classroomid);
 <   ALTER TABLE ONLY public.heldin DROP CONSTRAINT heldin_pkey;
       public            postgres    false    216    216            �           2606    16847    hosts hosts_pkey 
   CONSTRAINT     g   ALTER TABLE ONLY public.hosts
    ADD CONSTRAINT hosts_pkey PRIMARY KEY (roomnr, building, subjectid);
 :   ALTER TABLE ONLY public.hosts DROP CONSTRAINT hosts_pkey;
       public            postgres    false    229    229    229            �           2606    16862    mentors mentors_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.mentors
    ADD CONSTRAINT mentors_pkey PRIMARY KEY (professorid, studentid);
 >   ALTER TABLE ONLY public.mentors DROP CONSTRAINT mentors_pkey;
       public            postgres    false    230    230            �           2606    16772    phone phone_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.phone
    ADD CONSTRAINT phone_pkey PRIMARY KEY (number, professorid);
 :   ALTER TABLE ONLY public.phone DROP CONSTRAINT phone_pkey;
       public            postgres    false    221    221            �           2606    16767    professor professor_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.professor
    ADD CONSTRAINT professor_pkey PRIMARY KEY (professorid);
 B   ALTER TABLE ONLY public.professor DROP CONSTRAINT professor_pkey;
       public            postgres    false    220            �           2606    16760    student student_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_pkey PRIMARY KEY (studentid);
 >   ALTER TABLE ONLY public.student DROP CONSTRAINT student_pkey;
       public            postgres    false    218            �           2606    16790    subject subject_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.subject
    ADD CONSTRAINT subject_pkey PRIMARY KEY (subjectid);
 >   ALTER TABLE ONLY public.subject DROP CONSTRAINT subject_pkey;
       public            postgres    false    224            �           2606    16832    takes takes_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.takes
    ADD CONSTRAINT takes_pkey PRIMARY KEY (studentid, examid);
 :   ALTER TABLE ONLY public.takes DROP CONSTRAINT takes_pkey;
       public            postgres    false    228    228            �           2606    16817    taughtby taughtby_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.taughtby
    ADD CONSTRAINT taughtby_pkey PRIMARY KEY (subjectid, professorid);
 @   ALTER TABLE ONLY public.taughtby DROP CONSTRAINT taughtby_pkey;
       public            postgres    false    227    227            �           2606    16700    heldin unique_exam_schedule 
   CONSTRAINT     e   ALTER TABLE ONLY public.heldin
    ADD CONSTRAINT unique_exam_schedule UNIQUE (examid, classroomid);
 E   ALTER TABLE ONLY public.heldin DROP CONSTRAINT unique_exam_schedule;
       public            postgres    false    216    216            �           2606    16798    exam exam_professorid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.exam
    ADD CONSTRAINT exam_professorid_fkey FOREIGN KEY (professorid) REFERENCES public.professor(professorid);
 D   ALTER TABLE ONLY public.exam DROP CONSTRAINT exam_professorid_fkey;
       public          postgres    false    226    4749    220            �           2606    16808    exam exam_roomnr_building_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.exam
    ADD CONSTRAINT exam_roomnr_building_fkey FOREIGN KEY (roomnr, building) REFERENCES public.classroom(roomnr, building);
 H   ALTER TABLE ONLY public.exam DROP CONSTRAINT exam_roomnr_building_fkey;
       public          postgres    false    222    222    4753    226    226            �           2606    16803    exam exam_subjectid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.exam
    ADD CONSTRAINT exam_subjectid_fkey FOREIGN KEY (subjectid) REFERENCES public.subject(subjectid);
 B   ALTER TABLE ONLY public.exam DROP CONSTRAINT exam_subjectid_fkey;
       public          postgres    false    224    226    4755            �           2606    16848     hosts hosts_roomnr_building_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.hosts
    ADD CONSTRAINT hosts_roomnr_building_fkey FOREIGN KEY (roomnr, building) REFERENCES public.classroom(roomnr, building);
 J   ALTER TABLE ONLY public.hosts DROP CONSTRAINT hosts_roomnr_building_fkey;
       public          postgres    false    222    222    229    229    4753            �           2606    16853    hosts hosts_subjectid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.hosts
    ADD CONSTRAINT hosts_subjectid_fkey FOREIGN KEY (subjectid) REFERENCES public.subject(subjectid);
 D   ALTER TABLE ONLY public.hosts DROP CONSTRAINT hosts_subjectid_fkey;
       public          postgres    false    224    4755    229            �           2606    16863     mentors mentors_professorid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.mentors
    ADD CONSTRAINT mentors_professorid_fkey FOREIGN KEY (professorid) REFERENCES public.professor(professorid);
 J   ALTER TABLE ONLY public.mentors DROP CONSTRAINT mentors_professorid_fkey;
       public          postgres    false    230    220    4749            �           2606    16868    mentors mentors_studentid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.mentors
    ADD CONSTRAINT mentors_studentid_fkey FOREIGN KEY (studentid) REFERENCES public.student(studentid);
 H   ALTER TABLE ONLY public.mentors DROP CONSTRAINT mentors_studentid_fkey;
       public          postgres    false    218    4747    230            �           2606    16773    phone phone_professorid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.phone
    ADD CONSTRAINT phone_professorid_fkey FOREIGN KEY (professorid) REFERENCES public.professor(professorid);
 F   ALTER TABLE ONLY public.phone DROP CONSTRAINT phone_professorid_fkey;
       public          postgres    false    4749    221    220            �           2606    16838    takes takes_examid_fkey    FK CONSTRAINT     x   ALTER TABLE ONLY public.takes
    ADD CONSTRAINT takes_examid_fkey FOREIGN KEY (examid) REFERENCES public.exam(examid);
 A   ALTER TABLE ONLY public.takes DROP CONSTRAINT takes_examid_fkey;
       public          postgres    false    4757    226    228            �           2606    16833    takes takes_studentid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.takes
    ADD CONSTRAINT takes_studentid_fkey FOREIGN KEY (studentid) REFERENCES public.student(studentid);
 D   ALTER TABLE ONLY public.takes DROP CONSTRAINT takes_studentid_fkey;
       public          postgres    false    4747    228    218            �           2606    16823 "   taughtby taughtby_professorid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.taughtby
    ADD CONSTRAINT taughtby_professorid_fkey FOREIGN KEY (professorid) REFERENCES public.professor(professorid);
 L   ALTER TABLE ONLY public.taughtby DROP CONSTRAINT taughtby_professorid_fkey;
       public          postgres    false    220    227    4749            �           2606    16818     taughtby taughtby_subjectid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.taughtby
    ADD CONSTRAINT taughtby_subjectid_fkey FOREIGN KEY (subjectid) REFERENCES public.subject(subjectid);
 J   ALTER TABLE ONLY public.taughtby DROP CONSTRAINT taughtby_subjectid_fkey;
       public          postgres    false    227    224    4755            @   0   x�31���t�442�22M�lS3C#cNgN#.C#S �rb���� �P�      9   $   x�3�0154�442".#S3cNCS�=... Sk	      D   Q   x�5���0��.�88����?Aj�{���T��G��^��A)�B1�j�����Y��v� S��.���8pۻ���&�      :   #   x�3�0154�4415�2253�4��0����� C6y      G   ,   x�31��0�t�4241421�251342�t�0115����� w�      H   "   x�31514�445515��f��@�	W� N:�      ?      x������ � �      >   b   x�3426�O-�K-��+-)����21514�t�LM��tN�)K�K-NI�����bȤ��z�0�Ci^fR�^f	�s~nAiIj�Bqrfj^r*'W� ��#      <   M   x�345515��L)K����)����K�4200�54 "�?N��d �eT阓Z���R���+#�b_��=... ���      B   a   x�324142���+)�O)M.���S(�WpI,ILJ,N-�2+������Ԍ�W.)�(-J-VH�KQp�I�/�,��cj��X�f��B�1z\\\ �h(w      F       x�345515�4415�22�M͸b���� =�:      E   (   x�3241421�41514�0115�455134����� _�u     