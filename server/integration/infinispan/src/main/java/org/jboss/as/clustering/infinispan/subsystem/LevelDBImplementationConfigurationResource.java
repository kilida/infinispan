/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2012, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.as.clustering.infinispan.subsystem;

import org.infinispan.persistence.leveldb.configuration.LevelDBStoreConfiguration;
import org.jboss.as.controller.AttributeDefinition;
import org.jboss.as.controller.PathElement;
import org.jboss.as.controller.SimpleAttributeDefinition;
import org.jboss.as.controller.SimpleAttributeDefinitionBuilder;
import org.jboss.as.controller.operations.validation.EnumValidator;
import org.jboss.as.controller.registry.AttributeAccess;
import org.jboss.dmr.ModelNode;
import org.jboss.dmr.ModelType;

/**
 * Resource description for the addressable resource
 *
 *    /subsystem=infinispan/cache-container=X/cache=Y/store=Z/implementation=IMPLEMENTATION
 *
 * @author Galder Zamarreño
 * @author Tristan Tarrant
 */
public class LevelDBImplementationConfigurationResource extends CacheChildResource {

    public static final PathElement PATH = PathElement.pathElement(ModelKeys.IMPLEMENTATION, ModelKeys.IMPLEMENTATION_NAME);

    static final SimpleAttributeDefinition TYPE =
            new SimpleAttributeDefinitionBuilder(ModelKeys.IMPLEMENTATION, ModelType.STRING, true)
                    .setXmlName(Attribute.TYPE.getLocalName())
                    .setAllowExpression(true)
                    .setFlags(AttributeAccess.Flag.RESTART_RESOURCE_SERVICES)
                    .setValidator(new EnumValidator<>(LevelDBStoreConfiguration.ImplementationType.class, true, false))
                    .setDefaultValue(new ModelNode().set(LevelDBStoreConfiguration.ImplementationType.AUTO.name()))
                    .build();

    static final AttributeDefinition[] ATTRIBUTES = {TYPE};


    public LevelDBImplementationConfigurationResource(RestartableResourceDefinition parent) {
        super(PATH, ModelKeys.IMPLEMENTATION, parent, ATTRIBUTES);
    }

}
