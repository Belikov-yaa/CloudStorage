----
---- PostgreSQL database dump
----
--
---- Dumped from database version 9.6.21
---- Dumped by pg_dump version 13.2
--
---- Started on 2021-04-28 23:37:24
--
--SET statement_timeout = 0;
--SET lock_timeout = 0;
--SET idle_in_transaction_session_timeout = 0;
--SET client_encoding = 'WIN1256';
--SET standard_conforming_strings = on;
--SELECT pg_catalog.set_config('search_path', '', false);
--SET check_function_bodies = false;
--SET xmloption = content;
--SET client_min_messages = warning;
--SET row_security = off;
--
--SET default_tablespace = '';
--
----
---- TOC entry 186 (class 1259 OID 16388)
---- Name: user; Type: TABLE; Schema: public; Owner: postgres
----

CREATE TABLE public.user (
    id integer NOT NULL,
    login character varying(25) NOT NULL,
    passhash character(64) NOT NULL,
    blocked boolean NOT NULL
);


ALTER TABLE public.user OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 16386)
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_id_seq OWNER TO postgres;

--
-- TOC entry 2131 (class 0 OID 0)
-- Dependencies: 185
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_id_seq OWNED BY public.user.id;


--
-- TOC entry 2004 (class 2604 OID 16391)
-- Name: user id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user ALTER COLUMN id SET DEFAULT nextval('public.user_id_seq'::regclass);

--
-- TOC entry 2132 (class 0 OID 0)
-- Dependencies: 185
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_id_seq', 1, false);


--
-- TOC entry 2006 (class 2606 OID 16393)
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


-- Completed on 2021-04-28 23:37:25

--
-- PostgreSQL database dump complete
--

