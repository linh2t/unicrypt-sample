/*
 * UniCrypt
 *
 *  UniCrypt(tm) : Cryptographical framework allowing the implementation of cryptographic protocols e.g. e-voting
 *  Copyright (C) 2014 Bern University of Applied Sciences (BFH), Research Institute for
 *  Security in the Information Society (RISIS), E-Voting Group (EVG)
 *  Quellgasse 21, CH-2501 Biel, Switzerland
 *
 *  Licensed under Dual License consisting of:
 *  1. GNU Affero General Public License (AGPL) v3
 *  and
 *  2. Commercial license
 *
 *
 *  1. This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU Affero General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU Affero General Public License for more details.
 *
 *   You should have received a copy of the GNU Affero General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *
 *  2. Licensees holding valid commercial licenses for UniCrypt may use this file in
 *   accordance with the commercial license agreement provided with the
 *   Software or, alternatively, in accordance with the terms contained in
 *   a written agreement between you and Bern University of Applied Sciences (BFH), Research Institute for
 *   Security in the Information Society (RISIS), E-Voting Group (EVG)
 *   Quellgasse 21, CH-2501 Biel, Switzerland.
 *
 *
 *   For further information contact <e-mail: unicrypt@bfh.ch>
 *
 *
 * Redistributions of files must retain the above copyright notice.
 */
package ch.bfh.unicrypt.math;

import ch.bfh.unicrypt.Example;
import ch.bfh.unicrypt.UniCryptException;
import ch.bfh.unicrypt.helper.array.classes.ByteArray;
import ch.bfh.unicrypt.helper.converter.classes.bytearray.BigIntegerToByteArray;
import ch.bfh.unicrypt.helper.converter.classes.string.BigIntegerToString;
import ch.bfh.unicrypt.helper.converter.interfaces.Converter;
import ch.bfh.unicrypt.helper.math.Alphabet;
import ch.bfh.unicrypt.math.algebra.concatenative.classes.StringElement;
import ch.bfh.unicrypt.math.algebra.concatenative.classes.StringMonoid;
import ch.bfh.unicrypt.math.algebra.dualistic.classes.Z;
import ch.bfh.unicrypt.math.algebra.dualistic.classes.ZElement;
import java.math.BigInteger;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rolf Haenni <rolf.haenni@bfh.ch>
 */
public class ConverterExample {

	public static void example1() {

		Converter<BigInteger, String> hexConverter = BigIntegerToString.getInstance(16);
		Converter<BigInteger, String> binConverter = BigIntegerToString.getInstance(2);
		Converter<BigInteger, ByteArray> bigEndian = BigIntegerToByteArray.getInstance(ByteOrder.BIG_ENDIAN);
		Converter<BigInteger, ByteArray> littleEndian = BigIntegerToByteArray.getInstance(ByteOrder.LITTLE_ENDIAN);

		Z z = Z.getInstance();

		List<ZElement> zElements = new ArrayList<>();
		try {
			zElements.add(z.getElement(BigInteger.valueOf(1000)));
			zElements.add(z.getElement(BigInteger.valueOf(-1000)));
			zElements.add(z.getElementFrom(2000));
			zElements.add(z.getElementFrom(1999));
			zElements.add(z.getElementFrom("1000"));
			zElements.add(z.getElementFrom("-1000"));
			zElements.add(z.getElementFrom("3e8", hexConverter));
			zElements.add(z.getElementFrom("-3e8", hexConverter));
			zElements.add(z.getElementFrom("1111101000", binConverter));
			zElements.add(z.getElementFrom("-1111101000", binConverter));
		} catch (UniCryptException ex) {
			Logger.getLogger(ConverterExample.class.getName()).log(Level.SEVERE, null, ex);
		}
		for (ZElement zElement : zElements) {
			Example.printLine(zElement,
							  zElement.convertToBigInteger(),
							  zElement.convertToString(),
							  zElement.convertTo(hexConverter),
							  zElement.convertTo(binConverter),
							  zElement.convertToByteArray(),
							  zElement.convertTo(bigEndian),
							  zElement.convertTo(littleEndian));
		}
	}

	public static void example2() {

		StringMonoid stringMonoid = StringMonoid.getInstance(Alphabet.LOWER_CASE);

		List<StringElement> stringElements = new ArrayList<>();
		stringElements.add(stringMonoid.getElement("Hallo"));

	}

	public static void main(final String[] args) {
		Example.runExamples();
	}

}
