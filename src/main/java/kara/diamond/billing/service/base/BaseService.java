package kara.diamond.billing.service.base;

import kara.diamond.billing.service.entity.IOperation;
import kara.diamond.billing.service.entity.Status;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

public abstract class BaseService {
	public abstract EntityManager getEntityManager();

    private void setParameters(Query query, Map<String, Object> parameters) {
        if(parameters == null || parameters.isEmpty()) return;
        if(query.getParameters() == null || query.getParameters().isEmpty()) return;
        for(Parameter<?> parameter : query.getParameters()) {
            if(parameters.containsKey(parameter.getName()))
                query.setParameter(parameter.getName(), parameters.get(parameter.getName()));
        }
    }

    protected <T> List<T> getByQuery(Class<T> type, String query, Map<String, Object> parameters, int firstResult, int maxResult) throws Exception {
        TypedQuery<T> typedQuery = getEntityManager().createQuery(query, type);
        setParameters(typedQuery, parameters);
        if(maxResult > 0) {
            typedQuery.setFirstResult(firstResult);
            typedQuery.setMaxResults(maxResult);
        }
        return typedQuery.getResultList();
    }

    protected <T> List<T> getByQuery(Class<T> type, String query, Map<String, Object> parameters) throws Exception {
        return getByQuery(type, query, parameters, 0, 0);
    }

    protected <T> List<T> getByQuery(Class<T> type, String query) throws Exception {
        return getByQuery(type, query, null);
    }

    protected <T> T getByPKey(Class<T> type, Object pKey) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("pkId", pKey);
        List<T> lst = getByQuery(type, "SELECT a FROM "+type.getSimpleName()+" a WHERE a.pkId = :pkId", parameters);
        if(lst == null || lst.isEmpty()) return null;
        else if(lst.size() > 1)
            throw new Exception("MultiResult");
        else
            return lst.get(0);
    }

    protected <T> List<T> getByField(Class<T> type, String fieldName, Object fieldValue) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(fieldName, fieldValue);
        StringBuilder query = new StringBuilder();
        query.append("SELECT a FROM ").append(type.getSimpleName())
                .append(" a WHERE a.").append(fieldName)
                .append(fieldValue != null && fieldValue instanceof Collection ? " IN " : " = ")
                .append(":").append(fieldName);
        return getByQuery(type, query.toString(), parameters);
    }

    protected <T> T getByQuerySingle(Class<T> type, String query, Map<String, Object> parameters) throws Exception {
        Query tmpQuery = getEntityManager().createQuery(query);
        setParameters(tmpQuery, parameters);
        Object obj = tmpQuery.getSingleResult();
        return obj == null ? null : type.cast(obj);
    }

    protected <T> T getByQuerySingle(Class<T> type, String query) throws Exception {
        return getByQuerySingle(type, query, null);
    }

    protected <T> List<T> getAll(Class<T> type) throws Exception {
        return getByQuery(type, new StringBuilder().append("SELECT a FROM ").append(type.getName()).append(" a").toString());
    }

    protected void insert(List<?> lst) throws Exception {
        lst.forEach(i -> getEntityManager().persist(i));
    }

    protected void insert(Object object) throws Exception {
        insert(Arrays.asList(object));
    }
    
    protected void insertImmediate(List<?> lst) throws Exception {
    	insert(lst);
    	getEntityManager().flush();
    }

    protected void update(List<?> lst) throws Exception {
        lst.forEach(i -> getEntityManager().merge(i));
    }

    protected void update(Object obj) throws Exception {
        update(Arrays.asList(obj));
    }

    protected void delete(List<?> lst) throws Exception {
        Object merged = null;
        for(Object obj : lst) {
            if(getEntityManager().contains(obj)) merged = obj;
            else merged = getEntityManager().merge(obj);
            getEntityManager().remove(merged);
        }
    }

    protected void delete(Object obj, boolean flush) throws Exception {
        delete(Arrays.asList(obj), flush);
    }

    protected void executeQuery(String query, Map<String, Object> parameters) throws Exception {
        Query tmpQuery = getEntityManager().createQuery(query);
        setParameters(tmpQuery, parameters);
        tmpQuery.executeUpdate();
    }

    protected void executeNativeQuery(String query) throws Exception {
        getEntityManager().createNativeQuery(query).executeUpdate();
    }
    
    protected static Exception getDatabaseException(Exception ex) {
		return ExceptionHelper.getActualException(ex);
	}
    
	protected <T> List<T> getByProcedure(String procedureName, Map<String, Object> parameters, String resultMapping, Class<T> type) throws Exception {
		StoredProcedureQuery query = getEntityManager().createStoredProcedureQuery(procedureName, resultMapping);
		return getByProcedureLocal(query, parameters, type);
	}
	
	protected <T> List<T> getByProcedure(String procedureName, Map<String, Object> parameters, Class<T> type) throws Exception {
		StoredProcedureQuery query = getEntityManager().createStoredProcedureQuery(procedureName, type);
		return getByProcedureLocal(query, parameters, type);
	}
	
	protected Map<String, List<?>> getByProcedure(String procedureName, Map<String, Object> parameters, List<Class<?>> lstType) throws Exception {
		StoredProcedureQuery query = getEntityManager().createStoredProcedureQuery(procedureName, lstType.toArray(new Class[lstType.size()]));
		setStoredProcedureParameter(query, parameters);
		query.execute();
		Map<String, List<?>> map = new HashMap<>();
		int i = 0;
		map.put(i+"", query.getResultList());
		while(query.hasMoreResults()) {
			map.put(++i + "", query.getResultList());
		}
		return map;
	}
	
	private <T> List<T> getByProcedureLocal(StoredProcedureQuery query, Map<String, Object> parameters, Class<T> type) throws Exception {
		setStoredProcedureParameter(query, parameters);
		List<T> lst = new ArrayList<>();
		List<?> lstResult = query.getResultList();
		if (lstResult != null) {
			lst.addAll(lstResult
				.stream()
				.map(i -> type.cast(i))
				.collect(Collectors.toList()));
		}
		return lst;
	}
	
	protected void execProcedure(String procedureName, Map<String, Object> parameters) throws Exception {
		StoredProcedureQuery query = getEntityManager().createStoredProcedureQuery(procedureName);
		setStoredProcedureParameter(query, parameters);
		query.execute();
	}
	
	private void setStoredProcedureParameter(StoredProcedureQuery query, Map<String, Object> parameters) {
		if (parameters != null && !parameters.isEmpty()) {
			for (Map.Entry<String, Object> entry : parameters.entrySet()) {
				query.registerStoredProcedureParameter(entry.getKey(), entry.getValue() != null ? entry.getValue().getClass() : null, ParameterMode.IN);
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
	}
	
	protected void modify(List<? extends IOperation> lst) throws Exception {
		if (lst == null) return;
		delete(lst.stream().filter(action -> action.getStatus() == Status.Delete).collect(Collectors.toList()));
		update(lst.stream().filter(action -> action.getStatus() == Status.Modify).collect(Collectors.toList()));
		insert(lst.stream().filter(action -> action.getStatus() == Status.Add).collect(Collectors.toList()));
		getEntityManager().flush();
	}
}
