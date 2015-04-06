using System;
using System.Drawing;
using System.Drawing.Imaging;
using System.IO;

namespace AC.QRCode
{
    public class QRCodeHelper
    {
        /// <summary>
        /// Get QR code image which contains content.
        /// </summary>
        /// <param name="content"></param>
        /// <returns></returns>
        public static Bitmap GetQRCodeImage(string content, int size)
        {
            if (size <= 0)
            {
                size = 4;
            }
            Image qrImage = QRCode.GetQRCode(content, "Byte", size, "0", "L");

            MemoryStream imageStream = new MemoryStream();

            qrImage.Save(imageStream, ImageFormat.Gif);
            qrImage.Dispose();

            byte[] imageContent = new Byte[imageStream.Length];

            imageStream.Position = 0;

            imageStream.Read(imageContent, 0, (int) imageStream.Length);

            return new Bitmap((Image) new Bitmap(imageStream));
            ;
        }
    }
}