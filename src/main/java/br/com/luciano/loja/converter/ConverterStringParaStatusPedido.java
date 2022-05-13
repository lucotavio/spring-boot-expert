package br.com.luciano.loja.converter;

import br.com.luciano.loja.enumeration.StatusPedido;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class ConverterStringParaStatusPedido implements Converter<String, StatusPedido> {
    @Override
    public StatusPedido convert(MappingContext<String, StatusPedido> context) {
        String status = context.getSource();
        return StatusPedido.valueOf(status);
    }
}
