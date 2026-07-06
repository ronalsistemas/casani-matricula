package com.rcasani.service;

import com.rcasani.model.Pago;

public interface IPagoService extends ICRUD<Pago, Integer> {

    Pago getPagoCostoso();
}
