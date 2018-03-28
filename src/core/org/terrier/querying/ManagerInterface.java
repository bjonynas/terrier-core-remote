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

public interface ManagerInterface {
    void setProperty(String key, String value);

    void setProperties(Properties p);

    void runSearchRequest(SearchRequest srq);

    Index getIndex();

    SearchRequest newSearchRequest(String QueryID);

    SearchRequest newSearchRequest(String QueryID, String query);

    String getInfo(SearchRequest srq);
}
