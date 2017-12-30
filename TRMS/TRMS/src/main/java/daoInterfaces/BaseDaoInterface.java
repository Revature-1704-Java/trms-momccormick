package daoInterfaces;

public interface BaseDaoInterface<T> {

	T getById(int id);
	void update(T newObj);
	void addNew(T newObj);

}
