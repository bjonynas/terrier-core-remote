/*
* Terrier - Terabyte Retriever
* Webpage: http://terrier.org
* Contact: terrier{a.}dcs.gla.ac.uk
* University of Glasgow - School of Computing Science
* http://www.gla.ac.uk/
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
* The Original Code is Manager.java.
*
* The Original Code is Copyright (C) 2004-2016 the University of Glasgow.
* All Rights Reserved.
*
* Contributor(s):
*   Craig Macdonald <craigm{a.}dcs.gla.ac.uk> (original author)
*   Vassilis Plachouras <vassilis{a.}dcs.gla.ac.uk>
*/
package org.terrier.querying;
import gnu.trove.TIntArrayList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.terrier.matching.Matching;
import org.terrier.matching.MatchingQueryTerms;
import org.terrier.matching.Model;
import org.terrier.matching.QueryResultSet;
import org.terrier.matching.ResultSet;
import org.terrier.matching.dsms.BooleanScoreModifier;
import org.terrier.matching.models.WeightingModel;
import org.terrier.matching.models.WeightingModelFactory;
import org.terrier.querying.parser.FieldQuery;
import org.terrier.querying.parser.Query;
import org.terrier.querying.parser.QueryParser;
import org.terrier.querying.parser.QueryParserException;
import org.terrier.querying.parser.RequirementQuery;
import org.terrier.querying.parser.SingleTermQuery;
import org.terrier.structures.Index;
import org.terrier.terms.BaseTermPipelineAccessor;
import org.terrier.terms.TermPipelineAccessor;
import org.terrier.utility.ApplicationSetup;
/**
 * This class is responsible for handling/co-ordinating the main high-level
 * operations of a query. These are:
 * <li>Pre Processing (Term Pipeline, Control finding, term aggregration)</li>
 * <li>Matching</li>
 * <li>Post-processing @see org.terrier.querying.PostProcess </li>
 * <li>Post-filtering @see org.terrier.querying.PostFilter </li>
 * &lt;/ul&gt;
 * Example usage:
 * <pre>
 * Manager m = new Manager(index);
 * SearchRequest srq = m.newSearchRequest("Q1", "term1 title:term2");
 * m.runSearchRequest(srq);
 * </pre>
 * <p>
 * <b>Properties</b><ul>
 * <li><tt>querying.default.controls</tt> - sets the default controls for each query</li>
 * <li><tt>querying.allowed.controls</tt> - sets the controls which a users is allowed to set in a query</li>
 * <li><tt>querying.postprocesses.order</tt> - the order post processes should be run in</li>
 * <li><tt>querying.postprocesses.controls</tt> - mappings between controls and the post processes they should cause</li>
 * <li><tt>querying.preprocesses.order</tt> - the order pre processes should be run in</li>
 * <li><tt>querying.preprocesses.controls</tt> - mappings between controls and the pre processes they should cause</li>
 * <li><tt>querying.postfilters.order</tt> - the order post filters should be run in </li>
 * <li><tt>querying.postfilters.controls</tt> - mappings between controls and the post filters they should cause</li>
 * </ul>
 * <p><b>Controls</b><ul>
 * <li><tt>start</tt> : The result number to start at - defaults to 0 (1st result)</li>
 * <li><tt>end</tt> : the result number to end at - defaults to 0 (display all results)</li>
 * <li><tt>c</tt> : the c parameter for the DFR models, or more generally, the parameters for weighting model scheme</li>
 * </ul>
 */
public interface ManagerInterface {
    /** Provide a common interface for changing property values.
     * @param key Key of property to set
     * @param value Value of property to set */
    void setProperty(String key, String value);

    /** Set all these properties. Implemented using setProperty(String,String).
     * @param p All properties to set */
    void setProperties(Properties p);

    /**
     * This runs a given SearchRequest through the four retrieval stages and adds the ResultSet to the
     * SearchRequest object.
     * @param srq - the SearchRequest to be processed
     */
    void runSearchRequest(SearchRequest srq);

    Index getIndex();

    SearchRequest newSearchRequest(String QueryID);

    SearchRequest newSearchRequest(String QueryID, String query);

    String getInfo(SearchRequest srq);
}
