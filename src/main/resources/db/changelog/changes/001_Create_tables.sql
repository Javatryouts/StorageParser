CREATE TABLE public.boxes
(
    id int NOT NULL,
    contained_in int,
    CONSTRAINT boxes_pkey PRIMARY KEY (id),
    CONSTRAINT fkg237sjg80fo9ufpa235ymn1l5 FOREIGN KEY (contained_in)
        REFERENCES public.boxes (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.boxes
    OWNER to postgres;
    CREATE TABLE public.items
    (
        id int NOT NULL,
        color character varying(100) COLLATE pg_catalog."default",
        contained_in int,
        CONSTRAINT items_pkey PRIMARY KEY (id),
        CONSTRAINT fkbm3athjb8ijoou9j1b7k15x0q FOREIGN KEY (contained_in)
            REFERENCES public.boxes (id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

    ALTER TABLE public.items
        OWNER to postgres;