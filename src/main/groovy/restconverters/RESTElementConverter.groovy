package restconverters

interface RESTElementConverter<F, T> {
    T convert(F originObject)
}
