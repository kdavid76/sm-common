package com.bkk.sm.common.mongo

import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
import org.springframework.core.type.filter.AnnotationTypeFilter
import org.springframework.data.mapping.model.CamelCaseAbbreviatingFieldNamingStrategy
import org.springframework.data.mapping.model.FieldNamingStrategy
import org.springframework.data.mapping.model.PropertyNameFieldNamingStrategy
import org.springframework.data.mongodb.config.MongoConfigurationSupport
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import org.springframework.data.mongodb.core.convert.MongoCustomConversions.MongoConverterConfigurationAdapter
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoMappingContext
import org.springframework.util.ClassUtils
import org.springframework.util.StringUtils

abstract class MongoConfigurationSupportForSM {

    /**
     * Return the name of the database to connect to.
     *
     * @return must not be null.
     */
    protected abstract fun getDatabaseName(): String?

    /**
     * Returns the base packages to scan for MongoDB mapped entities at startup. Will return the package name of the
     * configuration class' (the concrete class, not this one here) by default. So if you have a
     * `com.acme.AppConfig` extending [MongoConfigurationSupport] the base package will be considered
     * `com.acme` unless the method is overridden to implement alternate behavior.
     *
     * @return the base packages to scan for mapped [Document] classes or an empty collection to not enable scanning
     * for entities.
     */
    private fun getMappingBasePackages(): Collection<String?> {
        val mappingBasePackage = javaClass.getPackage()
        return setOf(mappingBasePackage?.name)
    }

    /**
     * Configuration hook for [MongoCustomConversions] creation.
     *
     * @param converterConfigurationAdapter never null.
     * @see MongoConverterConfigurationAdapter.useNativeDriverJavaTimeCodecs
     * @see MongoConverterConfigurationAdapter.useSpringDataJavaTimeCodecs
     */
    protected abstract fun configureConverters(converterConfigurationAdapter: MongoConverterConfigurationAdapter?)

    /**
     * Scans the mapping base package for classes annotated with [Document]. By default, it scans for entities in
     * all packages returned by [.getMappingBasePackages].
     *
     * @see .getMappingBasePackages
     * @return
     * @throws ClassNotFoundException
     */
    @Throws(ClassNotFoundException::class)
    protected fun getInitialEntitySet(): Set<Class<*>> {
        val initialEntitySet: MutableSet<Class<*>> = HashSet()
        for (basePackage in getMappingBasePackages()) {
            initialEntitySet.addAll(scanForEntities(basePackage))
        }
        return initialEntitySet
    }

    /**
     * Scans the given base package for entities, i.e. MongoDB specific types annotated with [Document].
     *
     * @param basePackage must not be null.
     * @return
     * @throws ClassNotFoundException
     * @since 1.10
     */
    @Throws(ClassNotFoundException::class)
    protected fun scanForEntities(basePackage: String?): Set<Class<*>> {
        if (!StringUtils.hasText(basePackage)) {
            return emptySet()
        }
        val initialEntitySet: MutableSet<Class<*>> = HashSet()
        if (StringUtils.hasText(basePackage)) {
            val componentProvider = ClassPathScanningCandidateComponentProvider(
                false
            )
            componentProvider.addIncludeFilter(AnnotationTypeFilter(Document::class.java))
            for (candidate in componentProvider.findCandidateComponents(basePackage!!)) {
                initialEntitySet
                    .add(
                        ClassUtils.forName(
                            candidate.beanClassName!!,
                            MongoConfigurationSupport::class.java.classLoader
                        )
                    )
            }
        }
        return initialEntitySet
    }

    /**
     * Configures whether to abbreviate field names for domain objects by configuring a
     * [CamelCaseAbbreviatingFieldNamingStrategy] on the [MongoMappingContext] instance created.
     *
     * @return
     */
    private fun abbreviateFieldNames(): Boolean {
        return false
    }

    /**
     * Configures a [FieldNamingStrategy] on the [MongoMappingContext] instance created.
     *
     * @return
     * @since 1.5
     */
    protected fun fieldNamingStrategy(): FieldNamingStrategy {
        return if (abbreviateFieldNames()) CamelCaseAbbreviatingFieldNamingStrategy() else PropertyNameFieldNamingStrategy.INSTANCE
    }

    /**
     * Configure whether to automatically create indices for domain types by deriving the
     * [org.springframework.data.mongodb.core.index.IndexDefinition] from the entity or not.
     *
     * @return false by default. <br></br>
     * **INFO**: As of 3.x the default is set to false; In 2.x it was true.
     * @since 2.2
     */
    protected fun autoIndexCreation(): Boolean {
        return false
    }
}
