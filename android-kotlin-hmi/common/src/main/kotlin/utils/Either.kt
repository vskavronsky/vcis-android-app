package utils

typealias RootError = Error

sealed interface Either<out D, out E: RootError> {
    data class Success<out D>(val data: D): Either<D, Nothing>
    data class Error<out E: RootError>(val error: E): Either<Nothing, E>
}

fun <D> D.toSuccess(): Either<D, Nothing> = Either.Success(this)

fun <E : RootError> E.toError(): Either<Nothing, E> = Either.Error(this)