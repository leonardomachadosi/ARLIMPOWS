package br.ufma.lsdi.service;

import org.hibernate.service.spi.ServiceException;

import java.io.Serializable;
import java.util.List;

public interface CRUDService<E> {

	E save(E entity) throws ServiceException;
	
	E findById(Serializable id) throws ServiceException;
	
	List<E> findAll() throws ServiceException;
	
	void delete(Serializable id) throws ServiceException;
}