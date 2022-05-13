package br.com.luciano.loja.converter;

import br.com.luciano.loja.enumeration.StatusPedido;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class ConverterStatusPedidoParaString implements Converter<StatusPedido, String> {

    @Override
    public String convert(MappingContext<StatusPedido, String> mappingContext) {
        StatusPedido statusPedido = mappingContext.getSource();
        return statusPedido.toString();
    }
}
