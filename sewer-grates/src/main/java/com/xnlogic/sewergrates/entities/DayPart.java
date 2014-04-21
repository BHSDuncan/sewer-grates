package com.xnlogic.sewergrates.entities;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.KeyIndexableGraph;
import com.tinkerpop.blueprints.Vertex;
import com.xnlogic.sewergrates.exceptions.IllegalDatePartValueException;

public class DayPart extends DatePart
{
	private final String PROP_DAY = "Day";
	private final String EDGE_NEXT_DAY = "NEXT";
	
	private DayPart nextDay = null;
	
	private boolean isNewNextDay = false;
	
	public DayPart(int value) throws IllegalDatePartValueException
	{	
		super(value);
		
		if (value > 31)
			throw new IllegalDatePartValueException("Illegal day value: " + value);

	} // constructor
/*	
	// should have only one "next" day
	public void setNextDay(DayPart day)
	{
		this.nextDay = day;
		
		// set the "dirty" flag
		this.isNewNextDay = true;
	} // setNextDay
*/	
	public Vertex save(KeyIndexableGraph graph)
	{
		Vertex v = super.save(graph, this.PROP_DAY);

		// can set any additional properties, etc. here...
		return v;
	} // save
/*	
	private void updateNextDayEdge(KeyIndexableGraph graph)
	{
		// save the relationship with the next day, provided it doesn't yet exist
		Iterable<Edge> edges = this.v.getEdges(Direction.OUT, this.EDGE_NEXT_DAY);
		
		// if we don't have the edge, add it
		if (edges == null || !edges.iterator().hasNext())
		{
			this.v.addEdge(this.EDGE_NEXT_DAY, this.nextDay.getBackingVertex());
		}
		else
		{
			// if there exists a "next" edge already, update it if it's new
			assert(edges.iterator().hasNext());
			
			// get the edge
			Edge e = (Edge)(edges.iterator().next());

			// make sure we don't have any other ones before we update (i.e. remove/add) the edge
			assert(!edges.iterator().hasNext());

			// insert the new vertex in between so that the one edge becomes two (i.e. split the edge)
			Vertex vIn = e.getVertex(Direction.IN);  // head of edge (in this case, the "old" next day
			
			// only remove/add if the edge isn't already in there as-is
			if (this.isNewNextDay)
			{
				// get rid of the original edge
				graph.removeEdge(e);
				
				// create the edge from this day to the new next day
				graph.addEdge(null, this.v, this.nextDay.getBackingVertex(), this.EDGE_NEXT_DAY);
				
				// create the edge from the new next day to the old next day
				graph.addEdge(null, this.nextDay.getBackingVertex(), vIn, this.EDGE_NEXT_DAY);
			} // if
			
		} // if
	} // updateNextDayEdge
	*/
} // DayPart