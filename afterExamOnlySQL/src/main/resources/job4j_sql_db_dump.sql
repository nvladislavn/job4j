--
-- PostgreSQL database dump
--

-- Dumped from database version 12.0
-- Dumped by pg_dump version 12.0

-- Started on 2020-03-25 14:41:48

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
-- TOC entry 206 (class 1259 OID 26557)
-- Name: meetings_users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.meetings_users (
    meeting_id integer NOT NULL,
    user_id integer NOT NULL,
    user_status boolean DEFAULT false
);


ALTER TABLE public.meetings_users OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 26573)
-- Name: confirmed_app; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.confirmed_app AS
 SELECT meetings_users.meeting_id,
    count(*) AS count
   FROM public.meetings_users
  WHERE (meetings_users.user_status = true)
  GROUP BY meetings_users.meeting_id;


ALTER TABLE public.confirmed_app OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 26551)
-- Name: meetings; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.meetings (
    id integer NOT NULL,
    name character varying(250) NOT NULL
);


ALTER TABLE public.meetings OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 26549)
-- Name: meetings_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.meetings_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.meetings_id_seq OWNER TO postgres;

--
-- TOC entry 2849 (class 0 OID 0)
-- Dependencies: 204
-- Name: meetings_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.meetings_id_seq OWNED BY public.meetings.id;


--
-- TOC entry 203 (class 1259 OID 26543)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    name character varying(250) NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 26541)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- TOC entry 2850 (class 0 OID 0)
-- Dependencies: 202
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 2702 (class 2604 OID 26554)
-- Name: meetings id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meetings ALTER COLUMN id SET DEFAULT nextval('public.meetings_id_seq'::regclass);


--
-- TOC entry 2701 (class 2604 OID 26546)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 2842 (class 0 OID 26551)
-- Dependencies: 205
-- Data for Name: meetings; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.meetings (id, name) FROM stdin;
1	meeting_1
2	meeting_2
3	meeting_3
\.


--
-- TOC entry 2843 (class 0 OID 26557)
-- Dependencies: 206
-- Data for Name: meetings_users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.meetings_users (meeting_id, user_id, user_status) FROM stdin;
1	1	t
2	2	t
2	1	f
2	3	t
2	4	t
\.


--
-- TOC entry 2840 (class 0 OID 26543)
-- Dependencies: 203
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, name) FROM stdin;
1	John
2	Mike
3	Katy
4	Jane
\.


--
-- TOC entry 2851 (class 0 OID 0)
-- Dependencies: 204
-- Name: meetings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.meetings_id_seq', 3, true);


--
-- TOC entry 2852 (class 0 OID 0)
-- Dependencies: 202
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 4, true);


--
-- TOC entry 2707 (class 2606 OID 26556)
-- Name: meetings meetings_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meetings
    ADD CONSTRAINT meetings_pkey PRIMARY KEY (id);


--
-- TOC entry 2709 (class 2606 OID 26562)
-- Name: meetings_users meetings_users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meetings_users
    ADD CONSTRAINT meetings_users_pkey PRIMARY KEY (meeting_id, user_id);


--
-- TOC entry 2705 (class 2606 OID 26548)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2710 (class 2606 OID 26563)
-- Name: meetings_users meetings_users_meeting_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meetings_users
    ADD CONSTRAINT meetings_users_meeting_id_fkey FOREIGN KEY (meeting_id) REFERENCES public.meetings(id);


--
-- TOC entry 2711 (class 2606 OID 26568)
-- Name: meetings_users meetings_users_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meetings_users
    ADD CONSTRAINT meetings_users_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


-- Completed on 2020-03-25 14:41:48

--
-- PostgreSQL database dump complete
--

