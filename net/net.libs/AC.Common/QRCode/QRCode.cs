using System;
using System.Drawing;
using ThoughtWorks.QRCode.Codec;

namespace AC.QRCode
{

    public class QRCode
    {
        public static Image GetQRCode(string str, string enc, int sz, string ver, string corr)
        {
            if (sz < 0)
            {
                sz = 4;
            }

            QRCodeEncoder qrCodeEncoder = new QRCodeEncoder();
            String encoding = enc;
            if (encoding == "Byte")
            {
                qrCodeEncoder.QRCodeEncodeMode = QRCodeEncoder.ENCODE_MODE.BYTE;
            }
            else if (encoding == "AlphaNumeric")
            {
                qrCodeEncoder.QRCodeEncodeMode = QRCodeEncoder.ENCODE_MODE.ALPHA_NUMERIC;
            }
            else if (encoding == "Numeric")
            {
                qrCodeEncoder.QRCodeEncodeMode = QRCodeEncoder.ENCODE_MODE.NUMERIC;
            }
            try
            {
                int scale = Convert.ToInt16(sz);
                qrCodeEncoder.QRCodeScale = scale;
            }
            catch (Exception ex)
            {

                throw ex;
            }
            try
            {
                int version = Convert.ToInt16(ver);
                qrCodeEncoder.QRCodeVersion = version;
            }
            catch (Exception ex)
            {
                throw ex;
            }

            string errorCorrect = corr;
            if (errorCorrect == "L")
                qrCodeEncoder.QRCodeErrorCorrect = QRCodeEncoder.ERROR_CORRECTION.L;
            else if (errorCorrect == "M")
                qrCodeEncoder.QRCodeErrorCorrect = QRCodeEncoder.ERROR_CORRECTION.M;
            else if (errorCorrect == "Q")
                qrCodeEncoder.QRCodeErrorCorrect = QRCodeEncoder.ERROR_CORRECTION.Q;
            else if (errorCorrect == "H")
                qrCodeEncoder.QRCodeErrorCorrect = QRCodeEncoder.ERROR_CORRECTION.H;

            Image image;
            String data = str;
            image = qrCodeEncoder.Encode(data);

            return image;
        }
    }
}
