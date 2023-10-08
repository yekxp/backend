--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3
-- Dumped by pg_dump version 15.3

-- Started on 2023-10-07 21:56:36

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

--
-- TOC entry 3382 (class 1262 OID 26015)
-- Name: hire-a-senior; Type: DATABASE; Schema: -; Owner: postgres
--

\connect -reuse-previous=on "dbname='hire-a-senior'"

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

--
-- TOC entry 5 (class 2615 OID 26742)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 214 (class 1259 OID 26743)
-- Name: account_technologies; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.account_technologies (
                                             account_id character varying(255) NOT NULL,
                                             technology_id character varying(255) NOT NULL
);


ALTER TABLE public.account_technologies OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 26750)
-- Name: accounts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.accounts (
                                 id character varying(255) NOT NULL,
                                 available_period character varying(255),
                                 created_at timestamp(6) without time zone,
                                 currency character varying(255),
                                 date_of_birth timestamp(6) without time zone,
                                 email character varying(255),
                                 first_name character varying(255),
                                 hourly_price double precision,
                                 password character varying(255),
                                 role character varying(255),
                                 title smallint,
                                 updated_at timestamp(6) without time zone,
                                 verified boolean,
                                 CONSTRAINT accounts_role_check CHECK (((role)::text = ANY ((ARRAY['USER'::character varying, 'ADMIN'::character varying])::text[]))),
    CONSTRAINT accounts_title_check CHECK (((title >= 0) AND (title <= 1)))
);


ALTER TABLE public.accounts OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 26759)
-- Name: languages; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.languages (
                                  id character varying(255) NOT NULL,
                                  code character varying(255),
                                  name character varying(255)
);


ALTER TABLE public.languages OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 26766)
-- Name: languages_spoken; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.languages_spoken (
                                         account_id character varying(255) NOT NULL,
                                         language_id character varying(255) NOT NULL
);


ALTER TABLE public.languages_spoken OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 26773)
-- Name: reviews; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reviews (
                                id character varying(255) NOT NULL,
                                date timestamp(6) without time zone,
                                junior_id character varying(255),
                                rating character varying(255),
                                senior_id character varying(255),
                                text character varying(255)
);


ALTER TABLE public.reviews OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 26810)
-- Name: reviews_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.reviews_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reviews_seq OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 26780)
-- Name: session_requests; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.session_requests (
                                         id character varying(255) NOT NULL,
                                         created_at timestamp(6) without time zone,
                                         end_date timestamp(6) without time zone,
                                         hourly_price double precision,
                                         start_date timestamp(6) without time zone,
                                         status smallint,
                                         updated_at timestamp(6) without time zone,
                                         junior_id character varying(255),
                                         senior_id character varying(255),
                                         CONSTRAINT session_requests_status_check CHECK (((status >= 0) AND (status <= 4)))
);


ALTER TABLE public.session_requests OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 26788)
-- Name: sessions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sessions (
                                 id character varying(255) NOT NULL,
                                 created_at timestamp(6) without time zone,
                                 end_date timestamp(6) without time zone,
                                 hourly_price double precision,
                                 start_date timestamp(6) without time zone,
                                 status smallint,
                                 updated_at timestamp(6) without time zone,
                                 junior_id character varying(255),
                                 senior_id character varying(255),
                                 CONSTRAINT sessions_status_check CHECK (((status >= 0) AND (status <= 4)))
);


ALTER TABLE public.sessions OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 26796)
-- Name: technologies; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.technologies (
                                     id character varying(255) NOT NULL,
                                     code character varying(255),
                                     description character varying(255),
                                     name character varying(255)
);


ALTER TABLE public.technologies OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 26803)
-- Name: verify_email; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.verify_email (
                                     id character varying(255) NOT NULL,
                                     email character varying(255),
                                     token character varying(255)
);


ALTER TABLE public.verify_email OWNER TO postgres;

--
-- TOC entry 3210 (class 2606 OID 26749)
-- Name: account_technologies account_technologies_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_technologies
    ADD CONSTRAINT account_technologies_pkey PRIMARY KEY (account_id, technology_id);


--
-- TOC entry 3212 (class 2606 OID 26758)
-- Name: accounts accounts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accounts
    ADD CONSTRAINT accounts_pkey PRIMARY KEY (id);


--
-- TOC entry 3214 (class 2606 OID 26765)
-- Name: languages languages_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.languages
    ADD CONSTRAINT languages_pkey PRIMARY KEY (id);


--
-- TOC entry 3216 (class 2606 OID 26772)
-- Name: languages_spoken languages_spoken_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.languages_spoken
    ADD CONSTRAINT languages_spoken_pkey PRIMARY KEY (account_id, language_id);


--
-- TOC entry 3218 (class 2606 OID 26779)
-- Name: reviews reviews_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_pkey PRIMARY KEY (id);


--
-- TOC entry 3220 (class 2606 OID 26787)
-- Name: session_requests session_requests_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.session_requests
    ADD CONSTRAINT session_requests_pkey PRIMARY KEY (id);


--
-- TOC entry 3222 (class 2606 OID 26795)
-- Name: sessions sessions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sessions
    ADD CONSTRAINT sessions_pkey PRIMARY KEY (id);


--
-- TOC entry 3224 (class 2606 OID 26802)
-- Name: technologies technologies_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.technologies
    ADD CONSTRAINT technologies_pkey PRIMARY KEY (id);


--
-- TOC entry 3226 (class 2606 OID 26809)
-- Name: verify_email verify_email_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.verify_email
    ADD CONSTRAINT verify_email_pkey PRIMARY KEY (id);


--
-- TOC entry 3233 (class 2606 OID 26841)
-- Name: sessions fk345q9v9qb8un3uv2b1jnhlybb; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sessions
    ADD CONSTRAINT fk345q9v9qb8un3uv2b1jnhlybb FOREIGN KEY (junior_id) REFERENCES public.accounts(id);


--
-- TOC entry 3231 (class 2606 OID 26836)
-- Name: session_requests fk7y4r6hxhdtas5lmevupadf51o; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.session_requests
    ADD CONSTRAINT fk7y4r6hxhdtas5lmevupadf51o FOREIGN KEY (senior_id) REFERENCES public.accounts(id);


--
-- TOC entry 3229 (class 2606 OID 26826)
-- Name: languages_spoken fkd6d2aj4qdphxrfcssfl03nn3q; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.languages_spoken
    ADD CONSTRAINT fkd6d2aj4qdphxrfcssfl03nn3q FOREIGN KEY (account_id) REFERENCES public.accounts(id);


--
-- TOC entry 3230 (class 2606 OID 26821)
-- Name: languages_spoken fkfhyasepuj4h0twmg69dkou1dy; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.languages_spoken
    ADD CONSTRAINT fkfhyasepuj4h0twmg69dkou1dy FOREIGN KEY (language_id) REFERENCES public.languages(id);


--
-- TOC entry 3232 (class 2606 OID 26831)
-- Name: session_requests fkixh00d17s5fn7q1jqbwf6u46r; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.session_requests
    ADD CONSTRAINT fkixh00d17s5fn7q1jqbwf6u46r FOREIGN KEY (junior_id) REFERENCES public.accounts(id);


--
-- TOC entry 3227 (class 2606 OID 26811)
-- Name: account_technologies fkn563jqq8auna231nqly5ktipq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_technologies
    ADD CONSTRAINT fkn563jqq8auna231nqly5ktipq FOREIGN KEY (technology_id) REFERENCES public.technologies(id);


--
-- TOC entry 3228 (class 2606 OID 26816)
-- Name: account_technologies fkn8sfhh5xes6v25xj10ec7408; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_technologies
    ADD CONSTRAINT fkn8sfhh5xes6v25xj10ec7408 FOREIGN KEY (account_id) REFERENCES public.accounts(id);


--
-- TOC entry 3234 (class 2606 OID 26846)
-- Name: sessions fkr2eb4ppgldl82pho9k94a62i8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sessions
    ADD CONSTRAINT fkr2eb4ppgldl82pho9k94a62i8 FOREIGN KEY (senior_id) REFERENCES public.accounts(id);


--
-- TOC entry 3383 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;


-- Completed on 2023-10-07 21:56:36

--
-- PostgreSQL database dump complete
--

