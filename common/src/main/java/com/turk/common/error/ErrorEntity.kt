package com.turk.common.error

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureErrorEntity] class.
 */
sealed class ErrorEntity {

    object NetworkConnection : ErrorEntity()
    object ServerError : ErrorEntity()
    object AuthError : ErrorEntity()
    object Forbidden : ErrorEntity()
    object BadRequest : ErrorEntity()
    object NotFound : ErrorEntity()
    object UnSupportedMediaType : ErrorEntity()
    object MalFormedJson : ErrorEntity()
    object IllegalStateException : ErrorEntity()
    object JsonSyntaxException : ErrorEntity()
    object SocketTimedOutException : ErrorEntity()
    object InternalServerError : ErrorEntity()
    object AndroidError : ErrorEntity()
    object UniqueConstraintError : ErrorEntity()
    object UserNotFound : ErrorEntity()
    object NoRolesAvailable : ErrorEntity()
    object NoLanguagesAvailable : ErrorEntity()
    object FacebookLoginError : ErrorEntity()

    data class ApiRateLimitExceeded(val message:String) : ErrorEntity()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureErrorEntity : ErrorEntity()
}
