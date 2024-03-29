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
 * The Original Code is SinglePassIndexerFlushDelegate.java.
 *
 * The Original Code is Copyright (C) 2004-2016 the University of Glasgow.
 * All Rights Reserved.
 *
 * Contributor(s):
 *   Jonathon Hare <jsh2{a.}ecs.soton.ac.uk> (original author)
 *   
 */
package org.terrier.structures.indexing.singlepass;

import java.io.IOException;

/** Used by {@link ExtensibleSinglePassIndexer} for 
 * delegating the flushing of memory.
 * @author Jonathon Hare <jsh2{a.}ecs.soton.ac.uk>
 * @since 3.5
 */
public interface SinglePassIndexerFlushDelegate {
	/**
	 * force flush
	 * @throws IOException
	 */
	void forceFlush() throws IOException;
}
