/*
 * Copyright (c) 2014, Tim Verbelen
 * Internet Based Communication Networks and Services research group (IBCN),
 * Department of Information Technology (INTEC), Ghent University - iMinds.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *    - Redistributions of source code must retain the above copyright notice,
 *      this list of conditions and the following disclaimer.
 *    - Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *    - Neither the name of Ghent University - iMinds, nor the names of its 
 *      contributors may be used to endorse or promote products derived from 
 *      this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 */
package org.example.bye;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;

import org.example.api.Greeting;
import org.example.api.Person;

import aQute.bnd.annotation.component.Component;

@Component(properties =	{
		"greeting.type=bye"
	})
public class ExampleComponent implements Greeting {

	private final String hostname;
	
	public ExampleComponent(){
		hostname = getHostname();
	}
	
	@Override
	public String greet(String name) {
		return "Bye " + name +" (from server "+hostname+")";
	}

	@Override
	public String greet(Person person) {
		return "Bye "+ person.getFirstname()+" (from server "+hostname+")";
	}

	@Override
	public String greet(Collection<Person> persons) {
		String greet = "Bye ";
		for(Person p : persons){
			greet+=", "+p.getFirstname();
		}
		return greet+" (from server "+hostname+")";
	}
	
	private String getHostname(){
		String hostname = "unknown";
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("hostname").getInputStream()));
			hostname = reader.readLine();
		}catch(Exception ex){}
		
		return hostname;
	}
}