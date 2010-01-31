package org.jtpd.data.dao.hibernate;

import org.jtpd.data.dao.interfaces.EventDao;
import org.jtpd.data.dataobjects.Event;
/**
 * @author Richard Wilkinson - richard.wilkinson@jweekend.com
 *
 */
public class EventDaoHibernateImp extends AbstractDaoHibernateImpl<Event> implements EventDao {

	public EventDaoHibernateImp()
	{
		super(Event.class);
	}
}
