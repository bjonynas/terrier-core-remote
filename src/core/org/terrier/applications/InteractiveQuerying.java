/*
 * Terrier - Terabyte Retriever
 * Webpage: http://terrier.org
 * Contact: terrier{a.}dcs.gla.ac.uk
 * University of Glasgow - School of Computing Science
 * http://www.ac.gla.uk
 *
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
 * the License for the specific language governing rights and limitations
 * under the License.
 *
 * The Original Code is InteractiveQuerying.java.
 *
 * The Original Code is Copyright (C) 2004-2016 the University of Glasgow.
 * All Rights Reserved.
 *
 * Contributor(s):
 *   Gianni Amati <gba{a.}fub.it> (original author)
 *   Vassilis Plachouras <vassilis{a.}dcs.gla.ac.uk>
 *   Ben He <ben{a.}dcs.gla.ac.uk>
 *   Craig Macdonald <craigm{a.}dcs.gla.ac.uk>
 */
package org.terrier.applications;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.terrier.matching.ResultSet;
import org.terrier.querying.Manager;
import org.terrier.querying.SearchRequest;
import org.terrier.structures.Index;
import org.terrier.structures.MetaIndex;
import org.terrier.utility.ApplicationSetup;
/**
 * This class performs interactive querying at the command line. It asks
 * for a query on Standard Input, and then displays the document IDs that
 * match the given query.
 * <p><b>Properties:</b>
 * <ul><li><tt>interactive.model</tt> - which weighting model to use, defaults to PL2</li>
 * <li><tt>interactive.matching</tt> - which Matching class to use, defaults to Matching</li>
 * <li><tt>interactive.manager</tt> - which Manager class to use, defaults to Matching</li>
 * </ul>
 * @author Gianni Amati, Vassilis Plachouras, Ben He, Craig Macdonald
 */
public class InteractiveQuerying extends AbstractInteractiveQuerying{


	/** A default constructor initialises the index, and the Manager. */
	public InteractiveQuerying() {
		loadIndex();
		createManager();
	}

	/**
	 * Create a querying manager. This method should be overriden if
	 * another matching model is required.
	 */
	protected void createManager(){
		try{
			if (managerName.indexOf('.') == -1)
				managerName = "org.terrier.querying."+managerName;
			else if (managerName.startsWith("uk.ac.gla.terrier"))
				managerName = managerName.replaceAll("uk.ac.gla.terrier", "org.terrier");
			queryingManager = (Manager) (Class.forName(managerName)
					.getConstructor(new Class[]{Index.class})
					.newInstance(new Object[]{index}));
		} catch (Exception e) {
			logger.error("Problem loading Manager ("+managerName+"): ",e);
		}
	}

	/**
	 * Loads index(s) from disk.
	 *
	 */
	protected void loadIndex(){
		long startLoading = System.currentTimeMillis();
		index = Index.createIndex();
		if(index == null)
		{
			logger.error("Failed to load index. Perhaps index files are missing");
		}
		long endLoading = System.currentTimeMillis();
		if (logger.isInfoEnabled())
			logger.info("time to intialise index : " + ((endLoading-startLoading)/1000.0D));
	}

	/**
	 * Closes the used structures.
	 */
	public void close() {
		try{
			index.close();
		} catch (IOException ioe) {
			logger.warn("Problem closing index", ioe);
		}
	}

	/**
	 * According to the given parameters, it sets up the correct matching class.
	 * @param queryId String the query identifier to use.
	 * @param query String the query to process.
	 * @param cParameter double the value of the parameter to use.
	 */
	public void processQuery(String queryId, String query, double cParameter) {
		SearchRequest srq = ((Manager) queryingManager).newSearchRequest(queryId, query);
		srq.setControl("c", Double.toString(cParameter));
		srq.addMatchingModel(mModel, wModel);
		matchingCount++;
		((Manager) queryingManager).runPreProcessing(srq);
		((Manager) queryingManager).runMatching(srq);
		((Manager) queryingManager).runPostProcessing(srq);
		((Manager) queryingManager).runPostFilters(srq);
		try{
			printResults(resultFile, srq);
		} catch (IOException ioe) {
			logger.error("Problem displaying results", ioe);
		}
	}


	/**
	 * Starts the interactive query application.
	 * @param args the command line arguments.
	 */
	public static void main(String[] args) {
		InteractiveQuerying iq = new InteractiveQuerying();
		if (args.length == 0)
		{
			iq.processQueries(1.0);
		}
		else if (args.length == 1 && args[0].equals("--noverbose"))
		{
			iq.verbose = false;
			iq.processQueries(1.0);
		}
		else
		{
			iq.verbose = false;
			StringBuilder s = new StringBuilder();
			for(int i=0; i<args.length;i++)
			{
				s.append(args[i]);
				s.append(" ");
			}
			iq.processQuery("CMDLINE", s.toString(), 1.0);
		}
	}


}
