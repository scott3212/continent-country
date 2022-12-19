package com.continentcountry.translator;

/**
 *
 * @param <E> Entity Class
 * @param <I> Request Class
 * @param <O> Response Class
 */
public interface ObjectTranslator<E, I, O> {
	public O entityToResponse(E entity);

	public E requestToEntity(I request);
}
